package com.ssafy.domain.document;

import com.ssafy.api.request.ReviewPutReq;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Document("review")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
    @Id
    private String id;
    @Field(name = "reservation_id")
    private String reservationId; // 예약 정보
    @Field(name = "member_id")
    private Long memberId; // 유저 Id : 예약 정보에 포함하여 가져올 수 있지만 유저가 작성한 리뷰 정보를 가져올 때를 대비 추가
    private String nickname;
    private String profileImage;
    @Field(name = "place_id")
    private Long placeId; // 시설 Id값 : 예약 정보에 포함하여 가져올 수 있지만 시설에 대한 리뷰 정보를 조회할 때를 대비 추가
    @Field(name = "written_dt")
    private LocalDateTime writtenDt; // 작성 일자
    private String description; // 한줄 평
    private double cleanness; // 청결 점수 0 ~ 5.0
    private double place; // 시설 점수 0 ~ 5.0
    private double location; // 위치 점수 0 ~ 5.0
    private double price; // 가격 점수 0 ~ 5.0

    public void modifyReview(ReviewPutReq reviewInfo) {
        if( !"".equals(reviewInfo.getDescription()) )
            this.description = reviewInfo.getDescription();
        if(reviewInfo.getCleanness() != null)
            this.cleanness = reviewInfo.getCleanness();
        if(reviewInfo.getPlace() != null)
            this.place = reviewInfo.getPlace();
        if(reviewInfo.getLocation() != null)
            this.location = reviewInfo.getLocation();
        if(reviewInfo.getPrice() != null)
            this.price = reviewInfo.getPrice();
    }
}
