package com.ssafy.api.service;

import com.ssafy.api.request.PlaceSearchPostReq;
import com.ssafy.domain.entity.Member;
import com.ssafy.domain.entity.PlaceMongo;
import com.ssafy.domain.repository.PlaceMongoRepositorySupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("PlaceService")
public class PlaceService {

    private final MemberService memberService;
    private final PlaceMongoRepositorySupport placeMongoRepositorSupport;

    public PlaceService(MemberService memberService,
                        PlaceMongoRepositorySupport placeMongoRepositorSupport) {
        this.memberService = memberService;
        this.placeMongoRepositorSupport = placeMongoRepositorSupport;
    }

    public Page<PlaceMongo> placeSearch(Pageable pageable, PlaceSearchPostReq searchInfo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());
        Member member = memberService.getMemberById(memberId);

        // 검색 조건으로 추가된게 아니면 유저의 기본 주소를 통해 검색
        if(searchInfo.getSido() == null || searchInfo.getSido() == "") {
            searchInfo.setSido(member.getSido());
            searchInfo.setGugun(member.getGugun());
        }
        
        // 검색 운동 카테고리 정규식을 문자열로 바꾸기 ex) 축구|배드민턴|격투기
        String regexStr = searchInfo.getCategoryList().get(0);
        for(int i = 1; i < searchInfo.getCategoryList().size(); i++) {
            regexStr += "|" + searchInfo.getCategoryList().get(i);
        }
        searchInfo.getCategoryList().set(0, regexStr);

        return placeMongoRepositorSupport.searchPlace(pageable, searchInfo);
    }

}