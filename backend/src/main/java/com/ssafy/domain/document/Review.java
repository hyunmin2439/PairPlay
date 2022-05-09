package com.ssafy.domain.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.time.LocalDate;

@Document("review")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
    @Id
    String id;
    @Field(name = "member_id")
    Long memberId; // 유저 Id
    @Field(name = "place_id")
    Long placeId; // 시설 Id값
    @Field(name = "written_dt")
    LocalDate writtenDt; // 작성 일자
    String description; // 한줄 평
    double cleanness; // 청결 점수 0 ~ 5.0
    double place; // 시설 점수 0 ~ 5.0
    double location; // 위치 점수 0 ~ 5.0
    double price; // 가격 점수 0 ~ 5.0
}
