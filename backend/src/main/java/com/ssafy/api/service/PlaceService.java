package com.ssafy.api.service;

import com.ssafy.api.request.PlaceSearchPostReq;
import com.ssafy.domain.entity.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("PlaceService")
public class PlaceService {

    private final MemberService memberService;

    public PlaceService(MemberService memberService) {
        this.memberService = memberService;
    }

    public void placeSearch(Pageable pageable, PlaceSearchPostReq searchInfo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());

        Member member = memberService.getMemberById(memberId);
        if(searchInfo.getSido() == null || searchInfo.getSido() == "") {
            searchInfo.setSido(member.getSido());
            searchInfo.setGugun(member.getGugun());
        }

    }

}