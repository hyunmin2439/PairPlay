package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel("ReviewPostRequest")
public class ReviewPostReq {
    @ApiModelProperty(name="리뷰할 예약 정보", example="62722d8f5c1e32cfbf8eb719")
    String reservationId;
    @ApiModelProperty(name="리뷰 글", example="너무 좋은 체육 시설이었습니다.")
    String description; // 한줄 평
    @ApiModelProperty(name="청결 점수 0.0 ~ 5.0", example="5.0")
    Double cleanness;
    @ApiModelProperty(name="시설 점수 0.0 ~ 5.0", example="5.0")
    Double place;
    @ApiModelProperty(name="위치 점수 0.0 ~ 5.0", example="5.0")
    Double location;
    @ApiModelProperty(name="가격 점수 0.0 ~ 5.0", example="5.0")
    Double price;
}
