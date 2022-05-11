package com.ssafy.api.service;

import com.ssafy.api.request.*;
import com.ssafy.common.handler.CustomException;
import com.ssafy.domain.document.*;
import com.ssafy.domain.entity.Member;
import com.ssafy.domain.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

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

    private final ReservationRepositorySupport reservationRepositorySupport;

    public PlaceService(MemberService memberService,
                        PlaceRepository placeRepository,
                        ReviewRepository reviewRepository,
                        ReservationRepository reservationRepository,
                        PlaceRepositorySupport placeRepositorySupport,
                        PlaceMemberRepository placeMemberRepository,
                        ReservationRepositorySupport reservationRepositorySupport) {
        this.memberService = memberService;
        this.placeRepository = placeRepository;
        this.reviewRepository = reviewRepository;
        this.reservationRepository = reservationRepository;
        this.placeRepositorySupport = placeRepositorySupport;
        this.placeMemberRepository = placeMemberRepository;
        this.reservationRepositorySupport = reservationRepositorySupport;
    }

    /** 체육 시설 */
    public Place getPlace(Long placeId) {
        return placeRepository.findByPlaceId(placeId).orElse(null);
    }

    /** 예약 내역 */
    public Reservation getReservation(String reservationId) { return reservationRepository.findById(reservationId).orElse(null); }

    /** 리뷰 */
    public Review getReview(String reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    /** 체육 시설 리뷰 리스트 */
    public List<Review> getReviewList(Long placeId) {
        return reviewRepository.findByPlaceId(placeId);
    }

    /** 체육 시설 좋아요 개수 */
    public long getTotalLikeCnt(Long placeId) {
        return placeMemberRepository.countByLikeItems(placeId);
    }

    /** 유저가 체육 시설 좋아요를 눌렀는지 */
    public long getMemberLike(Long memberId, Long placeId) {
        return placeMemberRepository.countByMemberIdAndLikeItems(memberId, placeId);
    }

    /** 전체 합산 점수 */
    public double getTotalScore(List<Review> list) {
        double total = 0.0;

        for(int i = 0; i < list.size(); i++) {
            total += list.get(i).getCleanness() + list.get(i).getPlace() + list.get(i).getLocation() + list.get(i).getPrice();
        }

        return Math.round( ( total / (list.size() * 4) ) * 100.0 ) / 100.0;
    }

    /** 세부 항목의 총 점수와 전체 합산 점수 */
    public double[] getDetailScore(List<Review> list) {
        double[] score = new double[5];

        for(int i = 0; i < list.size(); i++) {
            score[0] += list.get(i).getCleanness();
            score[1] += list.get(i).getPlace();
            score[2] += list.get(i).getLocation();
            score[3] += list.get(i).getPrice();
        }

        score[4] = score[0] + score[1] + score[2] + score[3];
        score[4] = ( Math.round( score[4] / (list.size() * 4) ) * 100.0 ) / 100.0;

        score[0] = Math.round( ( score[0] / list.size() ) * 100.0 ) / 100.0;
        score[1] = Math.round( ( score[1] / list.size() ) * 100.0 ) / 100.0;
        score[2] = Math.round( ( score[2] / list.size() ) * 100.0 ) / 100.0;
        score[3] = Math.round( ( score[3] / list.size() ) * 100.0 ) / 100.0;

        return score;
    }

    /** 인증 정보에서 memberId값을 가져와 MongoDB에서 유저 정보를 검색 */
    public PlaceMember getPlaceMemberFromAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());
        return placeMemberRepository.findByMemberId(memberId).orElse(null);
    }

    /** 체육시설 상세 정보를 조회 */
    // 문제가 발생하더라도 rollback할 필요성이 없는 메서드라 Transactional 제외
    public PlaceDetail getDetailPlace(Long placeId) {
        PlaceMember placeMember = getPlaceMemberFromAuthentication();

        Place place = getPlace(placeId);
        if(place == null)
            throw new CustomException(FAIL_PLACE_NOT_FOUND);

        // viewCnt 증가
        place.increaseViewCnt();
        placeRepository.save(place);

        // 유저 최근 본 리스트에 저장 ( 10개 제한 둬야 함 )
        Queue<Long> queue = placeMember.getRecentItems();
        if( !queue.contains(placeId) ) {
            queue.offer(placeId);
            if(queue.size() > 10) queue.poll();
        }

        placeMemberRepository.save(placeMember);
        
        // 세부 평균 점수 담기
        PlaceDetail placeDetail = placeRepositorySupport.detailPlace(placeId);
        if( ! ( placeDetail.getReviewList() == null || placeDetail.getReviewList().isEmpty() ) )
            placeDetail.setDetailScore( getDetailScore(placeDetail.getReviewList()) );

        // 찜을 눌렀으면
        if( getMemberLike(placeMember.getMemberId(), placeId) > 0 )
            placeDetail.setLike();

        return placeDetail;
    }

    /** 체육시설 최근 본 리스트 */
    public Place getRecentViewPlaces() {
        Member member = memberService.getMemberFromAuthentication();
        return null;
    }

    /** 체육시설 인기 있는 리스트 */
    public Place getPopularPlaces() {
        Member member = memberService.getMemberFromAuthentication();
        return null;
    }

    /**
     * 체육시설 전체 목록
     * 전체를 조회하여 내부 필드값을 수정이 필요하여 사용
     */
    public List<MyReservation> allPlaces(Long memberId) {
        return reservationRepositorySupport.getMyReservation(memberId, 2);
    }

    /** 체육시설 장소를 검색 */
    public Page<Place> searchPlaces(Pageable pageable, PlaceSearchPostReq searchInfo) {
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
        return placeRepositorySupport.searchPlaces(pageable, searchInfo);
    }

    /** 체육시설 찜 등록/삭제 */
    @Transactional(rollbackOn = Exception.class)
    public void likePlace(Long placeId) {
        Place place = getPlace(placeId);
        PlaceMember placeMember = getPlaceMemberFromAuthentication();
        List<Long> likeList = placeMember.getLikeItems();

        if( likeList.contains(placeId) )
            likeList.remove(placeId);
        else
            likeList.add(placeId);

        placeMemberRepository.save(placeMember);

        place.modifyLikeCnt(getTotalLikeCnt(placeId));
        placeRepository.save(place);
    }

    /** 체육시설 리뷰 등록 */
    @Transactional(rollbackOn = Exception.class)
    public void postReview(ReviewPostReq reviewInfo) {
        Long memberId = memberService.getMemberIdFromAuthentication();

        // 예약된 정보가 없으면 리뷰 작성 불가
        Reservation reservation = getReservation(reviewInfo.getReservationId());
        if(reservation == null)
            throw new CustomException(FAIL_RESERVATION_NOT_FOUND);

        // 체육 시설 이용 이후가 아니라면 리뷰 작성 불가
        if( ! LocalDate.now().isAfter(reservation.getReservationDt()) )
            throw new CustomException(FAIL_NOT_YET_POST_REVIEW);

        // 예약 정보의 유저와 현재 로그인한 유저 정보가 다를 때 리뷰 작성 불가
        if(reservation.getMemberId() != memberId)
            throw new CustomException(FAIL_NOT_EQUAL_MEMBER);

        // 체육 시설이 조회가 안되면 예약 정보가 잘못 된 것
        Place place = getPlace(reservation.getPlaceId());
        if(place == null)
            throw new CustomException(FAIL_PLACE_NOT_FOUND);

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
            
            modifyReview(reviewPutInfo);
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

        // 예약 Collection에 reviewId 담기
        reservation.writtenReview(newReview.getId());
        reservationRepository.save(reservation);

        // place의 리뷰 개수와 점수 수정
        List<Review> reviewList = getReviewList(newReview.getPlaceId());
        if( !(reviewList == null || reviewList.isEmpty()) )
            place.modifyReviewScore(reviewList.size(), getTotalScore(reviewList));
        else
            place.modifyReviewScore(0, 0);

        placeRepository.save(place);
    }

    /** 체육시설 리뷰 수정 */
    @Transactional(rollbackOn = Exception.class)
    public void modifyReview(ReviewPutReq reviewInfo) {
        Review review = getReview(reviewInfo.getReviewId());

        // 리뷰가 존재하지 않으면
        if(review == null)
            throw new CustomException(FAIL_REVIEW_NOT_FOUND);

        // 체육 시설이 조회가 안되면 잘못된 리뷰
        Place place = getPlace(review.getPlaceId());
        if(place == null)
            throw new CustomException(FAIL_PLACE_NOT_FOUND);

        review.modifyReview(reviewInfo);
        reviewRepository.save(review);

        // place의 리뷰 개수와 점수 수정
        List<Review> reviewList = getReviewList(review.getPlaceId());
        if( !(reviewList == null || reviewList.isEmpty()) )
            place.modifyReviewScore(reviewList.size(), getTotalScore(reviewList));
        else
            place.modifyReviewScore(0, 0);

        placeRepository.save(place);
    }

    /** 체육시설 리뷰 삭제 */
    @Transactional(rollbackOn = Exception.class)
    public void deleteReview(ReviewDeleteReq reviewInfo) {
        // 리뷰가 존재하지 않으면
        Review review = getReview(reviewInfo.getReviewId());
        if(review == null)
            throw new CustomException(FAIL_REVIEW_NOT_FOUND);

        // 체육 시설이 조회가 안되면 예약 정보가 잘못 된 것
        Place place = getPlace(review.getPlaceId());
        if(place == null)
            throw new CustomException(FAIL_PLACE_NOT_FOUND);

        // 예약 정보가 존재하지 않으면
        Reservation reservation = getReservation(review.getReservationId());
        if(reservation == null)
            throw new CustomException(FAIL_RESERVATION_NOT_FOUND);

        reviewRepository.delete(review);

        // 지우고 조회해야 함 / place의 리뷰 개수와 점수 수정
        List<Review> reviewList = getReviewList(review.getPlaceId());
        if( !(reviewList == null || reviewList.isEmpty()) )
            place.modifyReviewScore(reviewList.size(), getTotalScore(reviewList));
        else
            place.modifyReviewScore(0, 0);

        placeRepository.save(place);

        reservation.deleteReview(); // 리뷰 삭제
        reservationRepository.save(reservation);
    }

    /** 체육시설 예약 등록 */
    public void reservePlace(ReservationPostReq reservationInfo) {
        // 예약 하려는 날짜가 현재 날짜보다 이전이면 ( 테스트의 편의를 위해 잠시 막아둠 )
//        if( reservationInfo.getReservationDt().isBefore(LocalDate.now()) )
//            throw new CustomException(FAIL_RESERVE_BEFORE_NOW_DATE);
        
        // 예약 정보에 담긴 시설 정보가 잘못되면
        Place place = getPlace(reservationInfo.getPlaceId());
        if(place == null)
            throw new CustomException(FAIL_PLACE_NOT_FOUND);

        Long memberId = memberService.getMemberIdFromAuthentication();

        Reservation reservation = Reservation.builder()
                .memberId(memberId)
                .placeId(reservationInfo.getPlaceId())
                .isWrittenReview(false)
                .reviewId("111111111111111111111111")
//                .createDt(LocalDateTime.now()) // throw Exception 주석 풀면서 같이 풀고 아래 메서드 지울 것!
                .createDt(reservationInfo.getReservationDt().atTime(0, 0))
                .reservationDt(reservationInfo.getReservationDt())
                .time(reservationInfo.getTime())
                .price(reservationInfo.getPrice())
                .build();

        reservationRepository.save(reservation);
    }

    /** 체육시설 예약 취소 */
    public void cancelPlace(ReservationDeleteReq reservationInfo) {
        Reservation reservation = getReservation(reservationInfo.getReservationId());

        // 예약된 정보가 없으면 리뷰 작성 불가
        if(reservation == null)
            throw new CustomException(FAIL_RESERVATION_NOT_FOUND);
        
        // 시설을 이용한 이후면
        if( LocalDate.now().isAfter(reservation.getReservationDt()) )
            throw new CustomException(FAIL_CANCEL_AFTER_RESERVATION_DATE);

        reservationRepository.delete(reservation);
    }
}