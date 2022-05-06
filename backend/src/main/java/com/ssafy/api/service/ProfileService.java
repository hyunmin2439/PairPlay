package com.ssafy.api.service;

import com.ssafy.api.request.ProfilePasswordPostReq;
import com.ssafy.api.request.ProfilePutReq;
import com.ssafy.api.response.CalendarDateRes;
import com.ssafy.common.handler.CustomException;
import com.ssafy.common.util.JwtTokenUtil;
import com.ssafy.domain.entity.Activity;
import com.ssafy.domain.entity.Mate;
import com.ssafy.domain.entity.Member;
import com.ssafy.domain.repository.ActivityRepository;
import com.ssafy.domain.repository.MateRepository;
import com.ssafy.domain.repository.MemberRepository;
import com.ssafy.domain.repository.PlaceReservationRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.ssafy.common.statuscode.MemberCode.FAIL_INVALID_PASSWORD;
import static com.ssafy.common.statuscode.ProfileCode.FAIL_MEMBER_NOT_FOUND;

@Service("profileService")
public class ProfileService {

    private final MemberRepository memberRepository;
    private final ActivityRepository activityRepository;
    private final MateRepository mateRepository;
    private final PlaceReservationRepository placeReservationRepository;

    public ProfileService(MemberRepository memberRepository, ActivityRepository activityRepository, MateRepository mateRepository, PlaceReservationRepository placeReservationRepository) {
        this.memberRepository = memberRepository;
        this.activityRepository = activityRepository;
        this.mateRepository = mateRepository;
        this.placeReservationRepository = placeReservationRepository;
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
    public List<CalendarDateRes> searchCalendar() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());

        // memberId, 오늘 날짜, 오늘 날짜 - 1year를 통해서 달력에 표시할 각 날짜의 mate의 count를 구한다
        List<CalendarDateRes> mateList = mateRepository.findByMemberIdAndMeetDtBefore(memberId, LocalDate.now(), LocalDate.now().minusYears(1));
        System.out.println(mateList.size());
        if (mateList.size() > 0) System.out.println(mateList.get(0));

        // memberId, 오늘 날짜, 오늘 날짜 - 1year를 통해서 달력에 표시할 각 날짜의 Place Reservation의 count를 구한다
        List<CalendarDateRes> reservationList = placeReservationRepository.findByMemberIdAndDateBetween(memberId, LocalDate.now(), LocalDate.now().minusYears(1));

        // mateList와 reservationList를 합쳐서 하나로 만든다
        List<CalendarDateRes> addList = new ArrayList<>();

        reservationList.forEach(date -> {
            boolean check = false;
            for (int i = 0; i < mateList.size(); i++) {
                if (date.getDate().equals(mateList.get(i).getDate())) {
                    mateList.get(i).setCount(mateList.get(i).getCount() + date.getCount());
                    check = true;
                }
                if (check) break;
            }
            if (!check) addList.add(date);
        });

        // mateList와 addList를 하나로 합친다


        return mateList;
    }

    // 해당 날짜에 참여한 모든 Activity 조회
    public List<Activity> searchCalendarDetail(LocalDate date) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());

        // memberId와 정확한 Date를 사용하여 Activity를 count
        List<Activity> list = activityRepository.findByIdAndMeetDtBetween(memberId, LocalDateTime.of(date, LocalTime.of(0, 0)), LocalDateTime.of(date, LocalTime.of(23, 59, 59)));

        return list;
    }

    // 해당 Activity에 참여한 모든 Mate 조회
    public List<Mate> searchActivityMate(Long activityId) {
        List<Mate> list = mateRepository.findByActivityId(activityId);
        return list;
    }

}
