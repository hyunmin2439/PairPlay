package com.ssafy.domain.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Document("reservation")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {
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
    @Field(name = "reservation_dt")
    private LocalDate reservationDt; // 예약 날짜
    private List<Integer> time; // 시간
    private int price; // 가격

    public void writtenReview(String reviewId) {
        this.isWrittenReview = true;
        this.reviewId = reviewId;
    }

    public void deleteReview() {
        this.isWrittenReview = false;
        this.reviewId = "";
    }
}
