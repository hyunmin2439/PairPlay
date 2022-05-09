package com.ssafy.api.service;

import com.ssafy.api.request.*;
import com.ssafy.common.handler.CustomException;
import com.ssafy.domain.document.PlaceMember;
import com.ssafy.domain.document.Reservation;
import com.ssafy.domain.document.Review;
import com.ssafy.domain.entity.Member;
import com.ssafy.domain.document.Place;
import com.ssafy.domain.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.ssafy.common.statuscode.CommonCode.EMPTY_REQUEST_VALUE;
import static com.ssafy.common.statuscode.PlaceCode.*;

@Service("PlaceService")
public class PlaceService {

    private final MemberService memberService;
    private final PlaceRepository placeRepository;
    private final ReviewRepository reviewRepository;
    private final ReservationRepository reservationRepository;
    private final PlaceRepositorySupport placeRepositorySupport;
    private final PlaceMemberRepository placeMemberRepository;

    public PlaceService(MemberService memberService,
                        PlaceRepository placeRepository,
                        ReviewRepository reviewRepository,
                        ReservationRepository reservationRepository,
                        PlaceRepositorySupport placeRepositorySupport,
                        PlaceMemberRepository placeMemberRepository) {
        this.memberService = memberService;
        this.placeRepository = placeRepository;
        this.reviewRepository = reviewRepository;
        this.reservationRepository = reservationRepository;
        this.placeRepositorySupport = placeRepositorySupport;
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
        
        // 검색 운동 카테고리가 비어 있으면 전체 검색
        if( searchInfo.getCategoryList() == null || searchInfo.getCategoryList().isEmpty() ){
            searchInfo.setCategoryList( new ArrayList<>() );
            searchInfo.getCategoryList().add(".*");
        }
        else {
            // 검색 운동 카테고리 정규식을 문자열로 바꾸기 ex) 축구|배드민턴|격투기
            String regexStr = searchInfo.getCategoryList().get(0);
            for(int i = 1; i < searchInfo.getCategoryList().size(); i++) {
                regexStr += "|" + searchInfo.getCategoryList().get(i);
            }
            searchInfo.getCategoryList().set(0, regexStr);
        }

        // 검색 단어가 없는 경우
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

    /**
     * 체육시설 리뷰 등록
     */
    public void postReview(ReviewPostReq reviewInfo) {
        Reservation reservation = reservationRepository.findById(reviewInfo.getReservationId()).orElse(null);

        // 예약된 정보가 없으면 리뷰 작성 불가
        if(reservation == null) 
            throw new CustomException(FAIL_RESERVATION_NOT_FOUND);
        
        // 체육 시설 이용 이후가 아니라면 리뷰 작성 불가
        if( ! LocalDate.now().isAfter(reservation.getReservationDt()) )
            throw new CustomException(FAIL_NOT_YET_POST_REVIEW);

        Long memberId = memberService.getMemberIdFromAuthentication();

        // 예약 정보의 유저와 현재 로그인한 유저 정보가 다를 때 리뷰 작성 불가
        if(reservation.getMemberId() != memberId)
            throw new CustomException(FAIL_NOT_EQUAL_MEMBER);

        // 만약 시설에 대한 리뷰를 작성했는데 다시 리뷰 작성 요청으로 온다면 수정으로 보내기
        Review review = reviewRepository.findById(reservation.getReviewId()).orElse(null);
        if( reservation.isWrittenReview() || review != null ){
            ReviewPutReq reviewPutInfo = new ReviewPutReq();
            reviewPutInfo.setReviewId(review.getId());
            reviewPutInfo.setDescription(reviewInfo.getDescription());
            reviewPutInfo.setCleanness(reviewInfo.getCleanness());
            reviewPutInfo.setPlace(reviewInfo.getPlace());
            reviewPutInfo.setLocation(reviewInfo.getLocation());
            reviewPutInfo.setPrice(reviewInfo.getPrice());
            
            modifyReview(new ReviewPutReq());
            return;
        }

        // 리뷰 등록 정보 중 하나라도 비어 있으면
        if(reviewInfo.getReservationId() == null ||
                reviewInfo.getDescription() == null ||
                reviewInfo.getCleanness() == null ||
                reviewInfo.getPlace() == null ||
                reviewInfo.getLocation() == null ||
                reviewInfo.getPrice() == null)
            throw new CustomException(EMPTY_REQUEST_VALUE);

        Review newReview = Review.builder()
                .reservationId(reviewInfo.getReservationId())
                .memberId(memberId)
                .placeId(reservation.getPlaceId())
                .writtenDt(LocalDateTime.now()) // MongoDB UTC로 저장됨
                .description(reviewInfo.getDescription())
                .cleanness(reviewInfo.getCleanness())
                .place(reviewInfo.getPlace())
                .location(reviewInfo.getLocation())
                .price(reviewInfo.getPrice())
                .build();

        reviewRepository.save(newReview);

        reservation.writtenReview(newReview.getId());
        reservationRepository.save(reservation);
    }

    /**
     * 체육시설 리뷰 수정
     */
    public void modifyReview(ReviewPutReq reviewInfo) {
        Review review = reviewRepository.findById(reviewInfo.getReviewId()).orElse(null);

        // 리뷰가 존재하지 않으면
        if(review == null)
            throw new CustomException(FAIL_REVIEW_NOT_FOUND);

        review.modifyReview(reviewInfo);
        reviewRepository.save(review);
    }

    /**
     * 체육시설 리뷰 삭제
     */
    public void deleteReview(ReviewDeleteReq reviewInfo) {
        Review review = reviewRepository.findById(reviewInfo.getReviewId()).orElse(null);

        // 리뷰가 존재하지 않으면
        if(review == null)
            throw new CustomException(FAIL_REVIEW_NOT_FOUND);

        Reservation reservation = reservationRepository.findById(review.getReservationId()).orElse(null);

        // 예약 정보가 없으면 안됨, 혹시 모를 DB 강제 수정 같은 것에 대비 하여 조건문 추가
        if(reservation != null) {
            reservation.deleteReview(); // 리뷰 삭제
            reservationRepository.save(reservation);
        }

        reviewRepository.delete(review);
    }

    /**
     * 체육시설 예약 등록
     */
    public void reserve (ReservationPostReq reservationInfo) {
        // 예약 하려는 날짜가 현재 날짜보다 이전이면 ( 테스트의 편의를 위해 잠시 막아둠 )
//        if( reservationInfo.getReservationDt().isBefore(LocalDate.now()) )
//            throw new CustomException(FAIL_RESERVE_BEFORE_NOW_DATE);

        Long memberId = memberService.getMemberIdFromAuthentication();

        Reservation reservation = Reservation.builder()
                .memberId(memberId)
                .placeId(reservationInfo.getPlaceId())
                .isWrittenReview(false)
                .reviewId("")
//                .createDt(LocalDateTime.now()) // throw Exception 주석 풀면서 같이 풀고 아래 메서드 지울 것!
                .createDt(reservationInfo.getReservationDt().atTime(0, 00))
                .reservationDt(reservationInfo.getReservationDt())
                .time(reservationInfo.getTime())
                .price(reservationInfo.getPrice())
                .build();

        reservationRepository.save(reservation);
    }

    /**
     * 체육시설 예약 취소
     */
    public void cancel(ReservationDeleteReq reservationInfo) {
        Reservation reservation = reservationRepository.findById(reservationInfo.getReservationId()).orElse(null);

        // 예약된 정보가 없으면 리뷰 작성 불가
        if(reservation == null)
            throw new CustomException(FAIL_RESERVATION_NOT_FOUND);
        
        // 시설을 이용한 이후면
        if( LocalDate.now().isAfter(reservation.getReservationDt()) )
            throw new CustomException(FAIL_CANCEL_AFTER_RESERVATION_DATE);

        reservationRepository.delete(reservation);
    }
}