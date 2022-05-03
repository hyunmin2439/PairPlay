package com.ssafy.api.service;

import com.ssafy.api.request.ProfilePasswordPostReq;
import com.ssafy.api.request.ProfilePutReq;
import com.ssafy.common.handler.CustomException;
import com.ssafy.common.util.JwtTokenUtil;
import com.ssafy.domain.entity.Activity;
import com.ssafy.domain.entity.Mate;
import com.ssafy.domain.entity.Member;
import com.ssafy.domain.repository.ActivityRepository;
import com.ssafy.domain.repository.MemberRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.ssafy.common.statuscode.ProfileCode.FAIL_MEMBER_NOT_FOUND;

@Service("profileService")
public class ProfileService {

    private final MemberRepository memberRepository;
    private final ActivityRepository activityRepository;

    public ProfileService(MemberRepository memberRepository, ActivityRepository activityRepository) {
        this.memberRepository = memberRepository;
        this.activityRepository = activityRepository;
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

    public void updateMemberProfileImage(MultipartHttpServletRequest request) {
        if (request != null) {
            // do something
        } else {
            // DB의 profile image url을 default 값으로 변경
        }
    }

    // JWT 토큰의 memberId 주인의 Password Update
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
    public List<Activity> searchCalendar() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());

        // memberId와 날짜 범위를 사용해서 Activity를 조회
        // repo -> List<Activity> findByIdAndMeetDtBetween(LocalDateTime 지금-기간, LocalDateTime 지금);
        List<Activity> list = activityRepository.findAll();

        return list;
    }

    // 해당 날짜에 참여한 모든 Activity 조회
    public List<Activity> searchCalendarDetail(LocalDate date) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());

        // memberId와 정확한 Date를 사용하여 Activity를 조회
        // repo -> List<Activity> findByIdAndMeetDt
        List<Activity> list = activityRepository.findAll();

        return list;
    }

    // 해당 Activity에 참여한 모든 Mate 조회
    public List<Mate> searchActivityMate(Long ActivityId) {
        // repo -> List<Mate> findById
        List<Mate> list = new ArrayList<>();

        return list;
    }
    
    // Activity와 Mate의 Repo를 추가/수정하여 DB에 접근할 수 있을 것

}
