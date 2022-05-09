package com.ssafy.api.service;

import com.ssafy.api.request.PlaceSearchPostReq;
import com.ssafy.domain.document.PlaceMember;
import com.ssafy.domain.entity.Member;
import com.ssafy.domain.document.Place;
import com.ssafy.domain.repository.PlaceMemberRepository;
import com.ssafy.domain.repository.PlaceRepository;
import com.ssafy.domain.repository.PlaceRepositorySupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("PlaceService")
public class PlaceService {

    private final MemberService memberService;
    private final PlaceRepository placeRepository;
    private final PlaceRepositorySupport placeRepositorySupport;
    private final PlaceMemberRepository placeMemberRepository;

    public PlaceService(MemberService memberService,
                        PlaceRepository placeRepository,
                        PlaceRepositorySupport placeMongoRepositorySupport,
                        PlaceMemberRepository placeMemberRepository) {
        this.memberService = memberService;
        this.placeRepository = placeRepository;
        this.placeRepositorySupport = placeMongoRepositorySupport;
        this.placeMemberRepository = placeMemberRepository;
    }

    /**
     * 인증 정보에서 memberId값을 가져와 MongoDB에서 유저 정보를 검색
     */
    public PlaceMember getPlaceMemberFromAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());
        return placeMemberRepository.findByMemberId(memberId).orElse(null);
    }

    /**
     * 체육시설 상세 정보를 조회
     */
    public Place getDetailPlace(Long placeId) {
        Member member = memberService.getMemberFromAuthentication();



        return null;
    }

    /**
     * 체육시설 장소를 검색
     */
    public Page<Place> searchPlace(Pageable pageable, PlaceSearchPostReq searchInfo) {
        Member member = memberService.getMemberFromAuthentication();

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

        // 검색 단어가 없을 경우
        if(searchInfo.getSearchWord() == null) {
            searchInfo.setSearchWord("");
        }

        // 최종 검색
        return placeRepositorySupport.searchPlace(pageable, searchInfo);
    }

    /**
     * 체육시설 찜 등록/삭제
     */
    public void likePlace(Long placeId) {
        PlaceMember placeMember = getPlaceMemberFromAuthentication();
        List<Long> likeList = placeMember.getLikeItems();

        if( likeList.contains(placeId) )
            likeList.remove(placeId);
        else
            likeList.add(placeId);

        placeMemberRepository.save(placeMember);
    }
}