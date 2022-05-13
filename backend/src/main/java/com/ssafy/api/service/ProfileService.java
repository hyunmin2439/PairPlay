package com.ssafy.api.service;

import com.ssafy.api.request.ProfilePasswordPostReq;
import com.ssafy.api.request.ProfilePutReq;
import com.ssafy.api.response.*;
import com.ssafy.common.handler.CustomException;
import com.ssafy.domain.document.MyReservation;
import com.ssafy.domain.document.Place;
import com.ssafy.domain.document.PlaceMember;
import com.ssafy.domain.entity.Activity;
import com.ssafy.domain.entity.Mate;
import com.ssafy.domain.entity.Member;
import com.ssafy.domain.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.ssafy.common.statuscode.ProfileCode.*;

@Service("profileService")
public class ProfileService {

    private final MemberRepository memberRepository;
    private final ActivityRepository activityRepository;
    private final MateRepository mateRepository;
    private final PlaceReservationRepository placeReservationRepository;
    private final PlaceRepository placeRepository;
    private final S3FileUploadService s3FileUploadService;
    private final ReservationRepository reservationRepository;
    private final ReservationRepositorySupport reservationRepositorySupport;

    public ProfileService(MemberRepository memberRepository, ActivityRepository activityRepository, MateRepository mateRepository,
                          PlaceReservationRepository placeReservationRepository, PlaceRepository placeRepository, S3FileUploadService s3FileUploadService,
                          ReservationRepository reservationRepository, ReservationRepositorySupport reservationRepositorySupport ) {
        this.memberRepository = memberRepository;
        this.activityRepository = activityRepository;
        this.mateRepository = mateRepository;
        this.placeReservationRepository = placeReservationRepository;
        this.placeRepository = placeRepository;
        this.s3FileUploadService = s3FileUploadService;
        this.reservationRepository = reservationRepository;
        this.reservationRepositorySupport = reservationRepositorySupport;
    }

//    public Member getMemberProfile(Long memberId) {
//        // DB에 유저 정보 조회
//        return memberRepository.findById(memberId).orElse(null);
//    }

    // JWT 토큰의 memberId 주인의 Profile Update
    public void updateMemberProfile(ProfilePutReq profilePutReq) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());

        Member member = memberRepository.findById(memberId).orElse(null);

        if (member == null)
            throw new CustomException(FAIL_MEMBER_NOT_FOUND);

        if (profilePutReq.getNickname() == null) profilePutReq.setNickname(member.getNickname());
        if (profilePutReq.getName() == null) profilePutReq.setName(member.getName());
        if (profilePutReq.getGender() == -1) profilePutReq.setGender(member.getGender());
        if (profilePutReq.getBirthDt() == null) profilePutReq.setBirthDt(member.getBirthDt());
        if (profilePutReq.getAddress() == null) profilePutReq.setAddress(member.getAddress());
        if (profilePutReq.getPhone() == null) profilePutReq.setPhone(member.getPhone());
        if (profilePutReq.getDescription() == null) profilePutReq.setDescription(member.getDescription());

        member.profileUpdate(profilePutReq);
        memberRepository.save(member);
    }

    // JWT 토큰의 memberId 주인의 ProfileImageUrl 변경
    public void updateMemberProfileImage(String fileName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());

        Member member = memberRepository.findById(memberId).orElse(null);

        if (member == null)
            throw new CustomException(FAIL_MEMBER_NOT_FOUND);

        member.profileImageUpdate(fileName);
        memberRepository.save(member);
    }

    // JWT 토큰의 memberId 주인의 Password Update
    // 현재 비밀번호와 동일한 비밀번호인지 확인을 하는 작업이 필요하다
    public void updateMemberPassword(ProfilePasswordPostReq profilePasswordPostReq) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());

        Member member = memberRepository.findById(memberId).orElse(null);

        if (member == null)
            throw new CustomException(FAIL_MEMBER_NOT_FOUND);

        member.passwordUpdate(profilePasswordPostReq.getPassword());
        memberRepository.save(member);
    }

    // 회원 탈퇴시, enable 를 false로 수정
    public void withdrawMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());

        Member member = memberRepository.findById(memberId).orElse(null);

        if (member == null)
            throw new CustomException(FAIL_MEMBER_NOT_FOUND);

        member.memberEnableUpdate(false);
        memberRepository.save(member);
    }

    // 달력 조회
    public List<CalendarDate> searchCalendar(Long memberId) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Long memberId = Long.parseLong(authentication.getName());

        // memberId, 오늘 날짜, 오늘 날짜 - 1year를 통해서 달력에 표시할 각 날짜의 mate의 count를 구한다
        List<CalendarDate> mateList = mateRepository.findByMemberIdAndMeetDtBefore(memberId, LocalDate.now().minusYears(1), LocalDate.now().plusDays(1));
        System.out.println(memberId + " " + LocalDate.now().minusYears(1) + " " + LocalDate.now());
        System.out.println(mateList.size());
        if (mateList.size() != 0) mateList.forEach(a -> System.out.println(a.getDate() + " " + a.getCount()));
        else System.out.println("empty");

        return mateList;
    }

    // 해당 날짜에 참여한 모든 Activity 조회
    @Transactional
    public List<CalendarDetailActivityRes> searchCalendarDetail(LocalDate date, Long memberId) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Long memberId = Long.parseLong(authentication.getName());

//        // memberId와 정확한 Date를 사용하여 Activity를 count
//        List<Activity> activityList = activityRepository.findByCreateIdAndMeetDtBetween(memberId, LocalDateTime.of(date, LocalTime.of(0, 0, 0)), LocalDateTime.of(date.plusDays(1), LocalTime.of(0, 0, 0)));
//        System.out.println(memberId + " " + LocalDateTime.of(date, LocalTime.of(0, 0, 0)) + " " + LocalDateTime.of(date.plusDays(1), LocalTime.of(0, 0, 0) ));
//        System.out.println(activityList.size());
//        if (activityList != null) {
//            System.out.println("not null");
//            activityList.forEach(a -> System.out.println(a.getId()));
//        }
//        else System.out.println("null");


        // memberId와 정확한 Date를 사용하여 Activity를 조회
        List<Activity> activityList = activityRepository.findByMateMemberIdAndMeetDtBetween(memberId, LocalDateTime.of(date, LocalTime.of(0, 0, 0)), LocalDateTime.of(date.plusDays(1), LocalTime.of(0, 0, 0)));
        System.out.println(memberId + " " + LocalDateTime.of(date, LocalTime.of(0, 0, 0)) + " " + LocalDateTime.of(date.plusDays(1), LocalTime.of(0, 0, 0) ));
        System.out.println(activityList.size());
        if (activityList.size() != 0) {
            System.out.println("not empty");
            activityList.forEach(a -> System.out.println(a.getId()));
        }
        else System.out.println("empty");


        // CalendarRetailActivityRes에 종합적으로 저장해서
        // 그것을 또 리스트로 만들어서 리턴한다
        List<CalendarDetailActivityRes> list = new ArrayList<>();

        activityList.forEach(activity -> {
            // activityId를 사용하여 MateList 구한다
            List<Mate> mateResList = mateRepository.findByActivityId_Id(activity.getId());
            for (Mate mate : mateResList) {
                System.out.println(mate.getMemberId().getId());
                System.out.println(mate.getActivityId().getId());
            }

            // CalendarDetailMateRes를 만들어서 그 안에 MateList를 사용하여 가져온 member_id, profile_image를 저장한다
            List<CalendarDetailMateRes> detailMateResList = new ArrayList<>();
            for (Mate mate : mateResList) {
                System.out.println(mate.getMemberId().getProfileImage());
                detailMateResList.add(CalendarDetailMateRes.of(mate.getMemberId().getId(),
                        s3FileUploadService.findImg(mate.getMemberId().getProfileImage())));
            }
            System.out.println(detailMateResList.size());

            // 임의의 CalendarDetailAvticityRes를 만들어서 그 안에 Activity정보와 MateList를 저장후 list에 더한다
            list.add(CalendarDetailActivityRes.of(activity, detailMateResList));
        });

        return list;
    }










    // 내가 만든 Activity의 Mate 신청 수신
    @Transactional
    public ProfileMateRes searchMateReceived(Pageable pageable) {
        // search의 결과는 Mate List
        // return 은 ProfileMateReceived의 List
        // 해당 값을 변환할 수 있어야 한다
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());

        Page<Mate> mateList = mateRepository.findByActivityId_CreateIdAndMemberId_IdNotAndActivityId_MeetDtAfterOrderById(memberId, memberId, LocalDateTime.now(), pageable);
        System.out.println(memberId + " " + LocalDateTime.now());
        System.out.println(mateList.getTotalPages());
        System.out.println(mateList.getTotalElements());

        List<ProfileMate> profileMateList = new ArrayList<>();
        mateList.forEach(mate -> {
            profileMateList.add(ProfileMate.of(mate, s3FileUploadService.findImg(mate.getMemberId().getProfileImage())));
        });

        return ProfileMateRes.of(mateList.getTotalPages(), mateList.getTotalElements(), profileMateList);
    }

    // 내가 발송한 Activity의 Mate 신청
    @Transactional
    public ProfileMateRes searchMateSend(Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());

        Page<Mate> mateList = mateRepository.findByMemberId_IdAndActivityId_CreateIdNotAndActivityId_MeetDtAfterOrderById(memberId, memberId, LocalDateTime.now(), pageable);
        System.out.println(memberId + " " + LocalDateTime.now());
        System.out.println(mateList.getTotalPages());
        System.out.println(mateList.getTotalElements());

        List<ProfileMate> profileMateList = new ArrayList<>();
        mateList.forEach(mate -> {
            Member member = memberRepository.findById(mate.getActivityId().getCreateId()).get();
            profileMateList.add(ProfileMate.of(mate, member, s3FileUploadService.findImg(member.getProfileImage()) ));
        });


        return ProfileMateRes.of(mateList.getTotalPages(), mateList.getTotalElements(), profileMateList);
    }

    // 메이트 신청 수락
    //// 현재 Activity는 나의 Activity일 것이고, 메이트를 보내서 해당 메이트를 수정하는 동작을 할 것
    //// 내가 이 Mate가 속한 Activity의 createId인 것을 확인을 해야하는가?
    @Transactional
    public void acceptMate(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());

        Mate mate = mateRepository.findById(id).orElse(null);
        System.out.println("memberId : " + memberId + " " + "req mateId : " + id);

        if (mate == null)
            throw new CustomException(FAIL_MATE_NOT_FOUND);
        System.out.println("createId : " + mate.getActivityId().getCreateId());

        if (mate.getActivityId().getCreateId() != memberId)
            throw new CustomException(FAIL_NOT_ACTIVITY_OWNER);

        mate.acceptMate();
        mateRepository.save(mate);
    }

    // 메이트 신청 거절
    //// 메이트 신청을 거절하면, Mate 테이블에서 해당 기록이 삭제된다
    //// ID를 받아서 Delete 하는 동작
    @Transactional
    public void rejectMate(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());

        Mate mate = mateRepository.findById(id).orElse(null);
        System.out.println("memberId : " + memberId + " " + "req mateId : " + id);

        if (mate == null)
            throw new CustomException(FAIL_MATE_NOT_FOUND);
        System.out.println("createId : " + mate.getActivityId().getCreateId());

        if (mate.getActivityId().getCreateId() != memberId)
            throw new CustomException(FAIL_NOT_ACTIVITY_OWNER);

        mateRepository.delete(mate);
    }

    // 메이트 신청 취소
    //// 메이트 신청을 취소하면, Mate 테이블에서 해당 기록이 삭제된다
    //// ID를 받아서 해당 Mate의 memberId가 JWT 토큰의 memberId와 같다면 삭제하는 동작
    @Transactional
    public void cancelMate(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());

        Mate mate = mateRepository.findById(id).orElse(null);
        System.out.println("memberId : " + memberId + " " + "req mateId : " + id);

        if (mate == null)
            throw new CustomException(FAIL_MATE_NOT_FOUND);
        System.out.println("Mate member_Id : " + mate.getMemberId().getId());

        if (mate.getMemberId().getId() != memberId)
            throw new CustomException(FAIL_NOT_MATE_OWNER);

        mateRepository.delete(mate);
    }







    // 예약 체육시설 조회
    //// 0 -> 전체
    //// 1 -> 사용 완료
    //// 2 -> 예약 중
    public ReservationListRes searchReservation(int page, int sw) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());

        List<MyReservation> list = reservationRepositorySupport.getMyReservation(memberId, sw);

        Long totalPages = Long.valueOf( list.size()/10 );
        Long totalElements = Long.valueOf( list.size() );
        System.out.println("TotalPages : " + totalPages + " || TotalElements : " + totalElements);

        int fromIdx = page * 10;
        int toIdx = fromIdx + 10;

        if (totalElements < toIdx) toIdx = Math.toIntExact(totalElements);
        if (page > totalPages) fromIdx = toIdx;
        System.out.println("fromIdx : " + fromIdx + " || toIdx : " + toIdx);

        list = list.subList(fromIdx, toIdx);

        List<ReservationRes> reservationResList = new ArrayList<>();

        System.out.println(LocalDateTime.now());
        list.forEach(myReservation -> {
            if (LocalDateTime.now().compareTo(myReservation.getReserveEndDt()) >= 0)
                reservationResList.add(ReservationRes.of(myReservation, true));
            else
                reservationResList.add(ReservationRes.of(myReservation, false));

            System.out.println("==========================");
            System.out.println(myReservation.getPrice());
            System.out.println(myReservation.getReserveStartDt());
            System.out.println(myReservation.getReserveEndDt());
            System.out.println("==========================");
        });

        return ReservationListRes.of(totalPages, totalElements, reservationResList);
    }



    // 찜한 체육시설 찾기
    public List<Place> searchPlaceLike(PlaceMember placeMember) {
        System.out.println(placeMember.getMemberId());
        System.out.println(placeMember.getLikeItems().size());
        return placeRepository.findByPlaceIdIn(placeMember.getLikeItems());
    }
}
