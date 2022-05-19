package com.ssafy.api.response;

import com.ssafy.domain.document.Review;
import io.swagger.annotations.ApiModel;
import lombok.*;
import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@ApiModel("Review")
public class ReviewRes extends BaseResponseBody {
    private String id;
    private String reservationId;
    private Long memberId;
    private String nickname;
    private String profileImage;
    private Long placeId;
    private LocalDateTime writtenDt; // 작성 일자
    private String reviewImage; // 리뷰 이미지
    private String description; // 한줄 평
    private double cleanness; // 청결 점수 0 ~ 5.0
    private double place; // 시설 점수 0 ~ 5.0
    private double location; // 위치 점수 0 ~ 5.0
    private double price; // 가격 점수 0 ~ 5.0

    public static ReviewRes of(Integer code, String message, Review review) {
        ReviewRes res = new ReviewRes();
        res.setCode(code);
        res.setMessage(message);
        res.setId(review.getId());
        res.setReservationId(review.getReservationId());
        res.setMemberId(review.getMemberId());
        res.setNickname(review.getNickname());
        res.setProfileImage(review.getProfileImage());
        res.setPlaceId(review.getPlaceId());
        res.setWrittenDt(review.getWrittenDt());
        res.setReviewImage(review.getReviewImage());
        res.setDescription(review.getDescription());
        res.setCleanness(review.getCleanness());
        res.setPlace(review.getPlace());
        res.setLocation(review.getLocation());
        res.setPrice(review.getPrice());
        return res;
    }
}
