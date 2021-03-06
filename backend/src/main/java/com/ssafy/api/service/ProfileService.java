package com.ssafy.api.service;

import com.ssafy.api.request.ProfilePasswordPostReq;
import com.ssafy.api.request.ProfilePutReq;
import com.ssafy.api.response.*;
import com.ssafy.common.handler.CustomException;
import com.ssafy.domain.document.MyReservation;
import com.ssafy.domain.document.Place;
import com.ssafy.domain.document.PlaceMember;
import com.ssafy.domain.entity.Activity;
import com.ssafy.domain.entity.ActivityLike;
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
    private final ActivityLikeRepository activityLikeRepository;
    private final ReservationRepositorySupport reservationRepositorySupport;

    public ProfileService(MemberRepository memberRepository, ActivityRepository activityRepository, MateRepository mateRepository,
                          PlaceReservationRepository placeReservationRepository, PlaceRepository placeRepository, S3FileUploadService s3FileUploadService,
                          ReservationRepository reservationRepository, ActivityLikeRepository activityLikeRepository, ReservationRepositorySupport reservationRepositorySupport ) {
        this.memberRepository = memberRepository;
        this.activityRepository = activityRepository;
        this.mateRepository = mateRepository;
        this.placeReservationRepository = placeReservationRepository;
        this.placeRepository = placeRepository;
        this.s3FileUploadService = s3FileUploadService;
        this.reservationRepository = reservationRepository;
        this.activityLikeRepository = activityLikeRepository;
        this.reservationRepositorySupport = reservationRepositorySupport;
    }

//    public Member getMemberProfile(Long memberId) {
//        // DB??? ?????? ?????? ??????
//        return memberRepository.findById(memberId).orElse(null);
//    }

    // JWT ????????? memberId ????????? Profile Update
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

    // JWT ????????? memberId ????????? ProfileImageUrl ??????
    public void updateMemberProfileImage(String fileName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());

        Member member = memberRepository.findById(memberId).orElse(null);

        if (member == null)
            throw new CustomException(FAIL_MEMBER_NOT_FOUND);

        member.profileImageUpdate(fileName);
        memberRepository.save(member);
    }

    // JWT ????????? memberId ????????? Password Update
    // ?????? ??????????????? ????????? ?????????????????? ????????? ?????? ????????? ????????????
    public void updateMemberPassword(ProfilePasswordPostReq profilePasswordPostReq) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());

        Member member = memberRepository.findById(memberId).orElse(null);

        if (member == null)
            throw new CustomException(FAIL_MEMBER_NOT_FOUND);

        member.passwordUpdate(profilePasswordPostReq.getPassword());
        memberRepository.save(member);
    }

    // ?????? ?????????, enable ??? false??? ??????
    public void withdrawMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());

        Member member = memberRepository.findById(memberId).orElse(null);

        if (member == null)
            throw new CustomException(FAIL_MEMBER_NOT_FOUND);

        member.memberEnableUpdate(false);
        memberRepository.save(member);
    }

    // ?????? ??????
    public List<CalendarDate> searchCalendar(Long memberId) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Long memberId = Long.parseLong(authentication.getName());

        // memberId, ?????? ??????, ?????? ?????? - 1year??? ????????? ????????? ????????? ??? ????????? mate??? count??? ?????????
        // List<CalendarDate> mateList = mateRepository.findByMemberIdAndMeetDtBefore(memberId, LocalDate.now().minusYears(1), LocalDate.now().plusDays(1));
        // LocalDate.now()??? ????????????, ?????? ????????? ???????????? ??????, 9?????? ????????? ???, UTC??? ???????????????, 9????????? ????????? ????????????
        LocalDate currentTime = LocalDate.of(LocalDateTime.now().plusHours(9).getYear(), LocalDateTime.now().plusHours(9).getMonth(), LocalDateTime.now().plusHours(9).getDayOfMonth());
        List<CalendarDate> mateList = mateRepository.findByMemberIdAndMeetDtBefore(memberId, currentTime.minusYears(1), currentTime.plusDays(1));
        
        System.out.println("Variable : " + memberId + " " + currentTime.minusYears(1) + " " + currentTime.plusDays(1));
        System.out.println("LocalDateTime.now() : " + LocalDateTime.now());
        System.out.println("MateList Size : " + mateList.size());
        if (mateList.size() != 0) mateList.forEach(a -> System.out.println(a.getDate() + " " + a.getCount()));
        else System.out.println("empty");

        return mateList;
    }

    // ?????? ????????? ????????? ?????? Activity ??????
    @Transactional
    public List<CalendarDetailActivityRes> searchCalendarDetail(LocalDate date, Long memberId) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Long memberId = Long.parseLong(authentication.getName());

//        // memberId??? ????????? Date??? ???????????? Activity??? count
//        List<Activity> activityList = activityRepository.findByCreateIdAndMeetDtBetween(memberId, LocalDateTime.of(date, LocalTime.of(0, 0, 0)), LocalDateTime.of(date.plusDays(1), LocalTime.of(0, 0, 0)));
//        System.out.println(memberId + " " + LocalDateTime.of(date, LocalTime.of(0, 0, 0)) + " " + LocalDateTime.of(date.plusDays(1), LocalTime.of(0, 0, 0) ));
//        System.out.println(activityList.size());
//        if (activityList != null) {
//            System.out.println("not null");
//            activityList.forEach(a -> System.out.println(a.getId()));
//        }
//        else System.out.println("null");


        // memberId??? ????????? Date??? ???????????? Activity??? ??????
        List<Activity> activityList = activityRepository.findByMateMemberIdAndMeetDtBetween(memberId, LocalDateTime.of(date, LocalTime.of(0, 0, 0)), LocalDateTime.of(date, LocalTime.of(23, 59, 59) ));
        System.out.println("Variable : " + memberId + " " + LocalDateTime.of(date, LocalTime.of(0, 0, 0)) + " " + LocalDateTime.of(date, LocalTime.of(23, 59, 59) ));
        System.out.println("Return Size : " + activityList.size());
        if (activityList.size() != 0) {
            System.out.println("not empty");
            activityList.forEach(a -> System.out.println(a.getId()));
        }
        else System.out.println("empty");


        // CalendarRetailActivityRes??? ??????????????? ????????????
        // ????????? ??? ???????????? ???????????? ????????????
        List<CalendarDetailActivityRes> list = new ArrayList<>();

        activityList.forEach(activity -> {
            // activityId??? ???????????? MateList ?????????
            List<Mate> mateResList = mateRepository.findByActivityId_Id(activity.getId());
            for (Mate mate : mateResList) {
                System.out.println("Mate Info : " + mate.getMemberId().getId() + "\nActivity Info : " + mate.getActivityId().getId());
            }

            // CalendarDetailMateRes??? ???????????? ??? ?????? MateList??? ???????????? ????????? member_id, profile_image??? ????????????
            List<CalendarDetailMateRes> detailMateResList = new ArrayList<>();
            for (Mate mate : mateResList) {
                System.out.println("Mate ProfileImage : " + mate.getMemberId().getProfileImage());
                detailMateResList.add(CalendarDetailMateRes.of(mate.getMemberId().getId(),
                        s3FileUploadService.findImg(mate.getMemberId().getProfileImage())));
            }
            System.out.println("Mate List Size : " + detailMateResList.size());

            // ????????? CalendarDetailAvticityRes??? ???????????? ??? ?????? Activity????????? MateList??? ????????? list??? ?????????
            list.add(CalendarDetailActivityRes.of(activity, detailMateResList));
        });

        return list;
    }










    // ?????? ?????? Activity??? Mate ?????? ??????
    @Transactional
    public ProfileMateRes searchMateReceived(Pageable pageable) {
        // search??? ????????? Mate List
        // return ??? ProfileMateReceived??? List
        // ?????? ?????? ????????? ??? ????????? ??????
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

    // ?????? ????????? Activity??? Mate ??????
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

    // ????????? ?????? ??????
    //// ?????? Activity??? ?????? Activity??? ?????????, ???????????? ????????? ?????? ???????????? ???????????? ????????? ??? ???
    //// ?????? ??? Mate??? ?????? Activity??? createId??? ?????? ????????? ????????????????
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

    // ????????? ?????? ??????
    //// ????????? ????????? ????????????, Mate ??????????????? ?????? ????????? ????????????
    //// ID??? ????????? Delete ?????? ??????
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

    // ????????? ?????? ??????
    //// ????????? ????????? ????????????, Mate ??????????????? ?????? ????????? ????????????
    //// ID??? ????????? ?????? Mate??? memberId??? JWT ????????? memberId??? ????????? ???????????? ??????
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







    // ?????? ???????????? ??????
    //// 0 -> ??????
    //// 1 -> ?????? ??????
    //// 2 -> ?????? ???
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
            if (LocalDateTime.now().plusHours(9).compareTo(myReservation.getReserveStartDt()) >= 0)
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



    // ?????? ???????????? ??????
    public ProfilePlaceLikeListRes searchPlaceLike(PlaceMember placeMember, int page) {
        System.out.println(placeMember.getMemberId());
        System.out.println(placeMember.getLikeItems().size());
        List<Place> list = placeRepository.findByPlaceIdIn(placeMember.getLikeItems());

        Long totalPages = Long.valueOf( list.size()/3 );
        Long totalElements = Long.valueOf( list.size() );
        System.out.println("TotalPages : " + totalPages + " || TotalElements : " + totalElements);

        int fromIdx = page * 3;
        int toIdx = fromIdx + 3;

        if (totalElements < toIdx) toIdx = Math.toIntExact(totalElements);
        if (page > totalPages) fromIdx = toIdx;
        System.out.println("fromIdx : " + fromIdx + " || toIdx : " + toIdx);

        list = list.subList(fromIdx, toIdx);

        return ProfilePlaceLikeListRes.of(totalPages, totalElements, list);
    }

    // ?????? ????????? ?????? ??????
    public Page<ActivityLike> searchActivityLike(Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());
        System.out.println(memberId);

        Page<ActivityLike> activityLikeList = activityLikeRepository.findByMemberId_Id(memberId, pageable);
        System.out.println(activityLikeList.getTotalPages());
        System.out.println(activityLikeList.getTotalElements());

        return activityLikeList;
    }
}
