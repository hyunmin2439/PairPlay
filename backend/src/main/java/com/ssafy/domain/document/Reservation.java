package com.ssafy.domain.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@Document("reservation")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {
    @Id
    String id;
    @Field(name = "member_id")
    Long memberId; // 유저 Id
    @Field(name = "place_id")
    Long placeId; // 시설 Id값
    @Field(name = "is_written_review")
    boolean isWrittenReview; // 리뷰 작성 여부
    @Field(name = "review_id")
    Long reviewId; // 리뷰 아이디
    @Field(name = "reservation_dt")
    LocalDate reservationDt; // 예약 날짜
    List<Integer> time; // 시간
    int price; // 가격
}
