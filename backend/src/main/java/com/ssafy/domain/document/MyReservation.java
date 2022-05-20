package com.ssafy.domain.document;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyReservation {
    @Id
    private String id;
    @Field(name = "member_id")
    private Long memberId; // 유저 Id
    @Field(name = "place_id")
    private Long placeId; // 시설 Id값
    @Field(name = "is_written_review")
    private boolean isWrittenReview; // 리뷰 작성 여부
    @Field(name = "review_id")
    private String reviewId; // 리뷰 아이디
    @Field(name = "create_dt")
    private LocalDateTime createDt; // 예약 요청한 날짜
    @Field(name = "reserve_start_dt")
    private LocalDateTime reserveStartDt; // 예약 사용 시작 시간
    @Field(name = "reserve_end_dt")
    private LocalDateTime reserveEndDt; // 예약 사용 종료 시간
    private List<Integer> time; // 시간
    private int price; // 가격

    List<Place> place;
    List<Review> review;
}
