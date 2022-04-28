package com.ssafy.api.service;

import com.ssafy.api.request.ProfilePasswordPostReq;
import com.ssafy.api.request.ProfilePutReq;
import com.ssafy.common.util.JwtTokenUtil;
import com.ssafy.domain.entity.Member;
import com.ssafy.domain.repository.MemberRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service("profileService")
public class ProfileService {

    private final MemberRepository memberRepository;

    public ProfileService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

//    public Member getMemberProfile(Long memberId) {
//        // DB에 유저 정보 조회
//        return memberRepository.findById(memberId).orElse(null);
//    }

    public void updateMemberProfile(ProfilePutReq profilePutReq) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());

        Member member = memberRepository.findById(memberId).orElse(null);
        member.profileUpdate(profilePutReq);

        memberRepository.save(member);
    }

    public void updateMemberProfileImage(MultipartHttpServletRequest request) {

    }

    public void updateMemberPassword(ProfilePasswordPostReq profilePasswordPostReq) {

    }

    public void withdrawMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());

        memberRepository.deleteById(memberId);
    }


}
