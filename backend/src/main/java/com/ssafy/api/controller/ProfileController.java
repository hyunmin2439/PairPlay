package com.ssafy.api.controller;

import com.ssafy.api.request.CalendarDateReq;
import com.ssafy.api.request.ProfilePasswordPostReq;
import com.ssafy.api.request.ProfilePutReq;
import com.ssafy.api.response.*;
import com.ssafy.api.service.MemberService;
import com.ssafy.api.service.ProfileService;
import com.ssafy.api.service.S3FileUploadService;
import com.ssafy.common.handler.CustomException;
import com.ssafy.domain.entity.Activity;
import com.ssafy.domain.entity.Mate;
import com.ssafy.domain.entity.Member;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
    private final PasswordEncoder passwordEncoder;

    public ProfileController (MemberService memberService, ProfileService profileService, S3FileUploadService s3FileUploadService, PasswordEncoder passwordEncoder) {
        this.memberService = memberService;
        this.profileService = profileService;
        this.s3FileUploadService = s3FileUploadService;
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
            @RequestParam(value = "profileImage", required = false) MultipartFile file) {
        if (file != null && file.getSize() != 0) {
            System.out.println(file.getOriginalFilename());
            System.out.println(file.getSize());

            try {
                String fileName = s3FileUploadService.upload(file);
                profileService.updateMemberProfileImage(fileName);
            } catch (Exception e) {
                e.printStackTrace();
                throw new CustomException(FAIL_PROFILE_IMAGE_S3_UPLOAD_ERROR);
            }
        } else {
            System.out.println("Default Profile");
            profileService.updateMemberProfileImage("default_profile.jpeg");
        }

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
    // 일정한 기간 중에서, 내가 참여한 Activity, 내가 예약한 시설에 대한 count를 조회
    @GetMapping("/calendar")
    @ApiOperation(value = "달력 조회", notes = "<string>JWT토큰</string>의 ID를 사용하여 <string>일정기간</string> 내의 활동 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "달력 조회에 성공했습니다.", response = ProfileRes.class),
    })
    public ResponseEntity<? extends CalendarActivityRes> searchCalendar() {
        List<CalendarDateRes> list = profileService.searchCalendar();

        return ResponseEntity.status(200).body(
                CalendarActivityRes.of(
                        SUCCESS_SEARCH_CALENDAR.getCode(),
                        SUCCESS_SEARCH_CALENDAR.getMessage(),
                        list
                )
        );
    }

    
    //// 개인활동 조회의 경우, 달력에서 하나의 날짜를 선택한 다음, 그 날짜에 해당하는 모든 것을 불러와야 한다
    //// 예약 + activity  둘 다 있어야 할 것 같다

    // 개인 활동 조회
    // 달력 중에서 날짜 하나를 선택해서 해당 날짜에 내가 참여한 Activity를 불러온다
    // Activity정보와 해당 Activity에 참여한 mate에 대한 유저 아이디, 유저 프로필사진이 필요하다
    // 날짜 정보를 받아서, 해당 날짜에 있는 Activity를 보여줘야하는건가?
    // Activity List를 받아서, 해당 Activity에 대한 정보를 보여줘야 하는건가?
    // Date를 보내주기로 되어있다
    // Date를 사용해서 Activity를 새롭게 Search하고, 각 Activity에 대해서 mate를 찾고, 해당 mate 중에
    // 본인이 아닌 사람에 대해서(?), 아니면 본인도 포함해서
    // mate list를 같이 보내준다
    // mate를 긁어오면 그 안에 있는 member나 Activity가 같이 긁혀올 것 -> Activity에 대한건 상관없을듯, member를 주는 것도 좋다
    // 하지만, 정보를 적당히 가공해서 of 함수를 만들어서 보낼 수 있을 것
    @PostMapping("/calendar/activity")
    @ApiOperation(value = "개인활동 조회", notes = "<string>JWT토큰</string>의 ID를 사용하여 <string>특정 날자</string>의 활동 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "개인활동 조회에 성공했습니다.", response = ProfileRes.class),
    })
    public ResponseEntity<? extends CalendarDetailRes> searchCalendarDetail(@RequestBody @ApiParam(value = "날짜", required = true)CalendarDateReq calendarDateReq) {
        List<Activity> activityList = profileService.searchCalendarDetail(calendarDateReq.getDate());
        List<Mate> mateList = new ArrayList<>();

        // 하나의 mateList에 여러 Activity의 모든 Mate를 넣는다
        // 구분을 위해서 각 Mate에 Activity Id를 추가적으로 달아주었음
        activityList.forEach(activity -> {
            List<Mate> list = profileService.searchActivityMate(activity.getId());
            mateList.addAll(list);
        });

        return ResponseEntity.status(200).body(
                CalendarDetailRes.of(
                        SUCCESS_SEARCH_CALENDAR_DATE.getCode(),
                        SUCCESS_SEARCH_CALENDAR_DATE.getMessage(),
                        activityList,
                        mateList
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
    @GetMapping("/places/reservation")
    @ApiOperation(value = "예약 체육시설 조회", notes = "<string>JWT토큰</string>의 ID를 사용하여 <string>예약한 체육시설</string>목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "예약된 체육시설 조회에 성공하였습니다.", response = ProfileRes.class),
    })
    public ResponseEntity<? extends ProfilePlaceRes> searchPlaceReservation() {

        List<ProfileReservationRes> list = new ArrayList<>();

        return ResponseEntity.status(200).body(
                ProfilePlaceRes.of(
                        SUCCESS_SEARCH_PLACE_RESERVATION.getCode(),
                        SUCCESS_SEARCH_PLACE_RESERVATION.getMessage(),
                        list
                )
        );
    }

    // 사용 완료 상품
    //// 예약 중인 상품과 다르게, 시설예약날짜+시간이 이미 지나간 상품
    @GetMapping("/places/used")
    @ApiOperation(value = "사용 완료한 체육시설 조회", notes = "<string>JWT토큰</string>의 ID를 사용하여 <string>사용 완료한 체육시설</string>목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "사용 완료한 체육시설 조회에 성공하였습니다.", response = ProfileRes.class),
    })
    public ResponseEntity<? extends ProfilePlaceRes> searchPlaceUsed() {

        List<ProfileReservationRes> list = new ArrayList<>();

        return ResponseEntity.status(200).body(
                ProfilePlaceRes.of(
                        SUCCESS_SEARCH_PLACE_RESERVATION.getCode(),
                        SUCCESS_SEARCH_PLACE_RESERVATION.getMessage(),
                        list
                )
        );
    }


//    // 찜한 상품
//    //// like 테이블을 활용하여, 시설 아이디를 얻어내고, 해당 아이디로 시설 조회
//    //// 찜한 시설의 경우는 조금 다르다
//    //// 찜한 시선은 그냥 내가 선택한 시설만 가져온다
//    //// 그렇지만, like 테이블을 조회한 다음, 해당 정보를 사용해서 시설정보를 가져올 것
//    //// 다른 DTO 하나를 추가로 만들어야 할 것
//    //// 그냥 시설 정보에 대한 것만 가져오면 되기 때문
//    @GetMapping("/places/like")
//    @ApiOperation(value = "찜한 체육시설 조회", notes = "<string>JWT토큰</string>의 ID를 사용하여 <string>찜한 체육시설</string>목록을 조회한다.")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "찜한 체육시설 조회에 성공하였습니다.", response = ProfileRes.class),
//    })
//    public ResponseEntity<? extends ProfilePlaceRes> searchPlaceLike() {
//
//    }



    // 내가 만든 Activity라는 것
    // Activity에서 memberId로 search
    // 또한, 해당 Activity에 신청을 한 신청자들에 대한 정보를 같이 넘겨준다
    // 신청자들을 수락/거절 할 수 있도록 할 수 있는 기능 존재
    // 현재 수락된 사람을 강퇴할 수 있는 기능 존재


    // 남이 만든 Activity라는 것
    // Mate에서 memberId로 search 한 다음, 내가 만든 것이 아닌 Activity를 의미할 것
    // 남이 만든 Activity에는 2가지 상태가 있을 수 있다
    // 현재 신청 수락 완료된 상태, 아니면 신청을 넣어만 놓은 상태
    // 해당 상태를 구분해서 볼 수 있어야 할 것


}
