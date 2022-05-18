package com.ssafy.api.controller;

import com.ssafy.api.request.CalendarDateReq;
import com.ssafy.api.request.ProfileMateApplyReq;
import com.ssafy.api.request.ProfilePasswordPostReq;
import com.ssafy.api.request.ProfilePutReq;
import com.ssafy.api.response.*;
import com.ssafy.api.service.MemberService;
import com.ssafy.api.service.PlaceService;
import com.ssafy.api.service.ProfileService;
import com.ssafy.api.service.S3FileUploadService;
import com.ssafy.common.handler.CustomException;
import com.ssafy.domain.document.Place;
import com.ssafy.domain.document.PlaceMember;
import com.ssafy.domain.entity.ActivityLike;
import com.ssafy.domain.entity.Member;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.ssafy.common.statuscode.CommonCode.EMPTY_REQUEST_VALUE;
import static com.ssafy.common.statuscode.ProfileCode.*;

@Api(value = "프로필 API", tags = {"Profile"})
@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {

    private final MemberService memberService;
    private final ProfileService profileService;
    private final S3FileUploadService s3FileUploadService;
    private final PlaceService placeService;
    private final PasswordEncoder passwordEncoder;

    public ProfileController (MemberService memberService, ProfileService profileService, S3FileUploadService s3FileUploadService, PlaceService placeService, PasswordEncoder passwordEncoder) {
        this.memberService = memberService;
        this.profileService = profileService;
        this.s3FileUploadService = s3FileUploadService;
        this.placeService = placeService;
        this.passwordEncoder = passwordEncoder;
    }

    // 테스트
    @GetMapping("")
    @ApiOperation(value = "테스트", notes = "테스트")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
    })
    public void test() {
        System.out.println("Hello Test");
    }

    // 유저 프로필 조회
    @GetMapping("/{memberId}")
    @ApiOperation(value = "유저 프로필 조회", notes = "전달 받은 <string>Id</string>로 유저 프로필을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "유저 프로필 조회에 성공했습니다.", response = ProfileRes.class),
    })
    public ResponseEntity<? extends ProfileRes> searchProfile(@PathVariable Long memberId) {
        Member member = memberService.getMemberById(memberId);
        // 프로필 이미지 fileName을 fileUrl로 변경
        member.profileImageUpdate(s3FileUploadService.findImg(member.getProfileImage()));

        return ResponseEntity.status(200).body(
                ProfileRes.of(
                    member,
                    SUCCESS_SEARCH_PROFILE.getCode(),
                    SUCCESS_SEARCH_PROFILE.getMessage()
                )
        );
    }

    // Profile Update
    @PutMapping("")
    @ApiOperation(value = "유저 프로필 수정", notes = "<string>JWT토큰</string>의 ID에 해당하는 회원 정보를 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "유저 프로필 수정에 성공했습니다.", response = BaseResponseBody.class),
    })
    public ResponseEntity<? extends BaseResponseBody> updateProfile(@RequestBody ProfilePutReq profilePutReq) {
        profileService.updateMemberProfile(profilePutReq);

        return ResponseEntity.status(200).body(
                BaseResponseBody.of(
                        SUCCESS_UPDATE_PROFILE.getCode(),
                        SUCCESS_UPDATE_PROFILE.getMessage()
                )
        );
    }
    
    // Profile Image Update
    @PostMapping("/profileImage")
    @ApiOperation(value = "유저 프로필사진 수정", notes = "<string>JWT토큰</string>의 ID에 해당하는 회원 프로필 사진을 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "유저 프로필 이미지 수정에 성공했습니다.", response = BaseResponseBody.class),
    })
    public ResponseEntity<? extends BaseResponseBody> updateProfileImage(
            @RequestPart(value = "file", required = false) MultipartFile imgFile
    ) throws IOException {
//        System.out.println("------------------------" + memberId + "---------------------------------");

        try {
            String fileName = s3FileUploadService.upload(imgFile);
            System.out.println(fileName == null ? "파일이름 null" : fileName);
            profileService.updateMemberProfileImage(fileName);
//            System.out.println(file);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(FAIL_PROFILE_IMAGE_S3_UPLOAD_ERROR);
        }

//        if (file != null) {
//            System.out.println("여기로 들어옴" + file.getName());
//            System.out.println(file.getOriginalFilename());
//            System.out.println(file.getSize());
//
//
//        } else {
//            System.out.println("Default Profile");
//            profileService.updateMemberProfileImage("default_profile.jpeg");
//        }

        return ResponseEntity.status(200).body(
                BaseResponseBody.of(
                        SUCCESS_UPDATE_PROFILE_IMAGE.getCode(),
                        SUCCESS_UPDATE_PROFILE_IMAGE.getMessage()
                )
        );
    }

    // Password Update
    @PostMapping("")
    @ApiOperation(value = "유저 비밀번호 수정", notes = "<string>JWT토큰</string>의 ID에 해당하는 회원 비밀번호를 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "비밀번호 변경에 성공했습니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 409, message = "현재 비밀번호와 동일한 비밀번호 입니다.", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> updatePassword(@RequestBody ProfilePasswordPostReq profilePasswordPostReq) {
        // 순환 참조 문제 해결 때문에 컨트롤러에서 암호화해서 넣기
        String password = profilePasswordPostReq.getPassword();

        if ("".equals(password) || password == null)
            throw new CustomException(EMPTY_REQUEST_VALUE);

        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());

        Member member = memberService.getMemberById(memberId);
        
        // 비밀번호 중복여부 검사
        if ( passwordEncoder.matches(password, member.getPassword()))
            throw new CustomException(FAIL_DUPLICATE_PASSWORD);

        profilePasswordPostReq.setPassword( passwordEncoder.encode(password) );
        profileService.updateMemberPassword(profilePasswordPostReq);

        return ResponseEntity.status(200).body(
                BaseResponseBody.of(
                        SUCCESS_UPDATE_PASSWORD.getCode(),
                        SUCCESS_UPDATE_PASSWORD.getMessage()
                )
        );
    }
    
    
    // 회원 탈퇴
    @DeleteMapping("")
    @ApiOperation(value = "회원 탈퇴", notes = "<string>JWT토큰</string>의 ID에 해당하는 회원이 탈퇴한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원 비활성화에 성공했습니다.", response = ProfileRes.class),
    })
    public ResponseEntity<? extends BaseResponseBody> withdrawMember() {
        profileService.withdrawMember();
        return ResponseEntity.status(200).body(
                BaseResponseBody.of(
                        SUCCESS_WITHDRAW_MEMBER.getCode(),
                        SUCCESS_WITHDRAW_MEMBER.getMessage()
                )
        );
    }


    // 달력 조회
    // 일정한 기간 중에서, 내가 참여한 Activity의 count를 조회
    @GetMapping("/calendar/{memberId}")
    @ApiOperation(value = "달력 조회", notes = "<string>JWT토큰</string>의 ID를 사용하여 <string>일정기간</string> 내의 달력을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "달력 조회에 성공했습니다.", response = ProfileRes.class),
    })
    public ResponseEntity<? extends BaseResponseBody> searchCalendar(@PathVariable("memberId") Long memberId) {

        List<CalendarDate> list = profileService.searchCalendar(memberId);
        if (list != null) list.forEach(a -> System.out.println(a));
        else System.out.println("null");

        return ResponseEntity.status(200).body(
                CalendarActivityRes.of(
                        SUCCESS_SEARCH_CALENDAR.getCode(),
                        SUCCESS_SEARCH_CALENDAR.getMessage(),
                        list
                )
        );
    }

    
    //// 개인활동 조회의 경우, 달력에서 하나의 날짜를 선택한 다음, 그 날짜에 해당하는 모든 것을 불러와야 한다
    // 개인 활동 조회
    // 달력 중에서 날짜 하나를 선택해서 해당 날짜에 내가 참여한 Activity를 불러온다
    @PostMapping("/calendar/activity")
    @ApiOperation(value = "개인활동 조회", notes = "<string>JWT토큰</string>의 ID를 사용하여 <string>특정 날자</string>의 활동 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "개인활동 조회에 성공했습니다.", response = ProfileRes.class),
    })
    public ResponseEntity<? extends CalendarDetailDateRes> searchCalendarDetail(@RequestBody @ApiParam(value = "날짜", required = true) CalendarDateReq calendarDateReq) {

        List<CalendarDetailActivityRes> calendarDetailActivityResList = profileService.searchCalendarDetail(calendarDateReq.getDate(), calendarDateReq.getMemberId());

        return ResponseEntity.status(200).body(
                CalendarDetailDateRes.of(
                        SUCCESS_SEARCH_CALENDAR_DATE.getCode(),
                        SUCCESS_SEARCH_CALENDAR_DATE.getMessage(),
                        calendarDetailActivityResList
                )
        );
    }






    
    // 체육시설 조회와 같은 시설을 보여주어야 할 것 같다
    // 비슷한 방식 + Reservation 정보를 담은 res가 필요할 것이다

    // ProfilePlaceRes -> ProfileResercationRes의 List를 가지고 있을 것
    // ProfileReservationRes -> Place의 정보와 Reservation정보를 담은 DTO

    // Search할 때, 지금 날짜와 시간을 통해서 Search한다
    // 지금 보다 미래에 사용 예약된 상품이라면, 예약 상품 조회 -> 이후, 해당 시설에 대한 정보 찾기
    // 지금 보다 과거에 사용 예약된 상품이라면, 사용 완료 상품 조회 -> 이후, 해당 시설에 대한 정보 찾기


    // 예약, 사용, 찜한
    // pageable



    // 예약 중인 상품
    //// place_reservation 테이블에서 기록을 읽어올 수 있을 것
    //// 읽어진 기록을 통해서 현재 예약 중인 상품을 알 수 있을 것이고, 해당 데이터를 MongoDB에 접근하여 시설정보를 알아온다
    //// 예약중 -> 시설예약날짜+시간이 아직 오지 않은 상품
    @GetMapping("/places/reservation/{page}")
    @ApiOperation(value = "예약 체육시설 조회", notes = "<string>JWT토큰</string>의 ID를 사용하여 <string>예약한 체육시설</string>목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "예약된 체육시설 조회에 성공하였습니다.", response = ProfileRes.class),
    })
    public ResponseEntity<? extends BaseResponseBody> searchReservation(@PathVariable("page") int page) {

        ReservationListRes res = profileService.searchReservation(page, 2);

        res.setCode(SUCCESS_SEARCH_PLACE_RESERVATION.getCode());
        res.setMessage(SUCCESS_SEARCH_PLACE_RESERVATION.getMessage());
        return ResponseEntity.status(200).body(res);
    }

    // 사용 완료 상품
    //// 예약 중인 상품과 다르게, 시설예약날짜+시간이 이미 지나간 상품
    @GetMapping("/places/used/{page}")
    @ApiOperation(value = "사용 완료한 체육시설 조회", notes = "<string>JWT토큰</string>의 ID를 사용하여 <string>사용 완료한 체육시설</string>목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "사용 완료한 체육시설 조회에 성공하였습니다.", response = ProfileRes.class),
    })
    public ResponseEntity<? extends BaseResponseBody> searchReservationUsed(@PathVariable("page") int page) {

        ReservationListRes res = profileService.searchReservation(page, 1);

        res.setCode(SUCCESS_SEARCH_PLACE_USED.getCode());
        res.setMessage(SUCCESS_SEARCH_PLACE_USED.getMessage());
        return ResponseEntity.status(200).body(res);
    }

    // 예약 중인 상품 + 사용 완료 상품
    //// 예약을 한 정보가 존재하는 모든 상품을 조회한다
    //// 그렇지만 예약에 대한 기록은 남겨두어야 할 것 -> 예약인지 사용중인지 구분하는 로직은 똑같이 존재해야함
    @GetMapping("/places/{page}")
    @ApiOperation(value = "예약 정보가 있는 체육시설 조회", notes = "<string>JWT토큰</string>의 ID를 사용하여 <string>예약한 정보가 있는 체육시설</string>목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "예약 정보가 있는 체육시설 조회에 성공하였습니다.", response = ProfileRes.class),
    })
    public ResponseEntity<? extends BaseResponseBody> searchReservationTotal(@PathVariable("page") int page) {

        ReservationListRes res = profileService.searchReservation(page, 0);

        res.setCode(SUCCESS_SEARCH_PLACE_RESERVATION_TOTAL.getCode());
        res.setMessage(SUCCESS_SEARCH_PLACE_RESERVATION_TOTAL.getMessage());
        return ResponseEntity.status(200).body(res);
    }
    



    // 찜한 체육시설
    //// 찜한 체육시설의 정보는 몽고DB의 member 테이블 안의 자신의 멤버를 보면 알 수 있다
    @GetMapping("/places/like/{page}")
    @ApiOperation(value = "찜한 체육시설 조회", notes = "JWT토큰의 ID를 사용하여 찜한 체육시설목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "찜한 체육시설 조회에 성공하였습니다.", response = ProfilePlaceLikeListRes.class),
    })
    public ResponseEntity<? extends BaseResponseBody> searchPlaceLike(@PathVariable("page") int page) {

        PlaceMember placeMember = placeService.getPlaceMemberFromAuthentication();
        ProfilePlaceLikeListRes res = profileService.searchPlaceLike(placeMember, page);

        res.setCode(SUCCESS_SEARCH_PLACE_LIKE.getCode());
        res.setMessage(SUCCESS_SEARCH_PLACE_LIKE.getMessage());
        return ResponseEntity.status(200).body(res);
    }

    // 찜한 메이트 공고
    @Transactional
    @GetMapping("/mates/like")
    @ApiOperation(value = "찜한 메이트 공고 조회", notes = "JWT토큰의 ID를 사용하여 찜한 메이트 공고목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "예약 정보가 있는 체육시설 조회에 성공하였습니다.", response = ProfileRes.class),
    })
    public ResponseEntity<? extends BaseResponseBody> searchMateLike(@PageableDefault(page = 0, size = 3) Pageable pageable) {
        Page<ActivityLike> page= profileService.searchActivityLike(pageable);

        return ResponseEntity.status(200).body(
                ProfileActivityLikeRes.of(
                        SUCCESS_SEARCH_MATE_LIKE.getCode(),
                        SUCCESS_SEARCH_MATE_LIKE.getMessage(),
                        page.getTotalPages(),
                        page.getTotalElements(),
                        page
                )
        );
    }














//    내가 보낸 메이트 + 메이트 공고가 내꺼가 아닌것
//    메이트 공고 주인의 프로필 이미지 전달
//      1. 신청 취소 => active: 0
//      2. 메이트 주인 => 거절 => active: 0
//    내가 받은 메이트 + 메이트 공고 주인 == 나
//    신청자의 프로필 이미지 전달
//      1. 신청 수락 => active: 1
//      2. 신청 거절 => active: 0
//      3. 상대가 신청 취소 => active: 0
//    모든 경우에서 결과가 나오면 메이트 목록에서 제거
//    페이지네이션 (Page: 1, Size: 3~4)

    // 내가 만든 Activity
    // 신청 수신한 것들에 대한 정보를 조회한다
    //// Activity 중에서 createId가 자신인 Activity를 찾고,
    //// 그 Activity에 넣은 mate 신청을 조회한다
    //// 자신에 대한 정보는 뺴야할 것이다
    //// 시간이 이미 지나간 Activity에 대한 정보는 뺴야할 것이다
    //// response -> [ Activity + Mate들의 상세 Member정보 ] 의 List -> Mate List
    @GetMapping("/mates/received")
    @ApiOperation(value = "메이트 신청 수신 조회", notes = "<string>JWT토큰</string>의 ID를 사용하여 <string>Activity CreateId</string>가 같은 Activity에 신청한 Mate목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "메이트 신청 수신 조회에 성공하였습니다.", response = ProfileRes.class),
    })
    public ResponseEntity<? extends BaseResponseBody> searchMateReceived(@PageableDefault(page = 0, size = 3) Pageable pageable) {

        ProfileMateRes res = profileService.searchMateReceived(pageable);

        res.setCode(SUCCESS_SEARCH_MATE_RECEIVED.getCode());
        res.setMessage(SUCCESS_SEARCH_MATE_RECEIVED.getMessage());
        return ResponseEntity.status(200).body(res);
    }

    // 남이 만든 Activity
    // 신청 발신한 것들에 대한 정보를 조회한다
    //// Mate 중에서 나의 Mate를 찾는다
    //// 그 중에서 Activity createId가 자신이 아닌 것을 찾는다
    //// 또한, Activity createId를 사용해서, 공고 주인에 대한 정보를 추가적으로 전달해야한다
    //// 시간이 이미 지나간 Activity에 대한 정보는 뺴야할 것이다
    //// response -> [ Activity + Mate들의 상세 Member정보 ] 의 List -> Mate List
    @GetMapping("/mates/send")
    @ApiOperation(value = "메이트 신청 발신 조회", notes = "<string>JWT토큰</string>의 ID를 사용하여 <string>Mate MemberId</string>가 Activity에 신청한 Mate목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "메이트 신청 발신 조회에 성공하였습니다.", response = ProfileMateRes.class),
    })
    public ResponseEntity<? extends BaseResponseBody> searchMateSend(@PageableDefault(page = 0, size = 3) Pageable pageable) {

        ProfileMateRes res = profileService.searchMateSend(pageable);

        res.setCode(SUCCESS_SEARCH_MATE_SEND.getCode());
        res.setMessage(SUCCESS_SEARCH_MATE_SEND.getMessage());
        return ResponseEntity.status(200).body(res);
    }
    
    // 신청 수락
    @PutMapping("/mates/accept")
    @ApiOperation(value = "메이트 신청 수락", notes = "<string>Mate ID</string>를 사용하여 <string>Mate테이블의 ACCEPT</string>를 1로 변경한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "메이트 신청을 수락하였습니다.", response = BaseResponseBody.class),
    })
    public ResponseEntity<? extends BaseResponseBody> acceptMate(@RequestBody ProfileMateApplyReq profileMateApplyReq) {

        profileService.acceptMate(profileMateApplyReq.getId());
        return ResponseEntity.status(200).body(BaseResponseBody.of(SUCCESS_MATE_ACCEPT.getCode(), SUCCESS_MATE_ACCEPT.getMessage()));
    }

    // 신청 거절
    @DeleteMapping("/mates/reject/{id}")
    @ApiOperation(value = "메이트 신청 거절", notes = "<string>Mate ID</string>를 사용하여 해당 Mate를 <string>삭제</string>한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "메이트 신청을 거절하였습니다.", response = BaseResponseBody.class),
    })
    public ResponseEntity<? extends BaseResponseBody> rejectMate(@PathVariable("id") Long mateId) {

        profileService.rejectMate(mateId);
        return ResponseEntity.status(200).body(BaseResponseBody.of(SUCCESS_MATE_REJECT.getCode(), SUCCESS_MATE_REJECT.getMessage()));
    }

    // 신청 취소
    @DeleteMapping("/mates/cancel/{id}")
    @ApiOperation(value = "메이트 신청 취소", notes = "<string>Mate ID와 JWT토큰</string>를 사용하여 해당 Mate를 <string>취소</string>한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "메이트 신청을 취소하였습니다.", response = BaseResponseBody.class),
    })
    public ResponseEntity<? extends BaseResponseBody> cancelMate(@PathVariable("id") Long mateId) {

        profileService.cancelMate(mateId);
        return ResponseEntity.status(200).body(BaseResponseBody.of(SUCCESS_MATE_CANCEL.getCode(), SUCCESS_MATE_CANCEL.getMessage()));
    }

}
