package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel("ReviewPutRequest")
public class ReviewPutReq extends ReviewDeleteReq {
    @ApiModelProperty(name="리뷰 글", example="너무 좋은 체육 시설이었습니다.")
    String description; // 한줄 평
    @ApiModelProperty(name="청결 점수 0.0 ~ 5.0, 수정 안할 시 아예 담지 말기", example="5.0")
    Double cleanness;
    @ApiModelProperty(name="시설 점수 0.0 ~ 5.0, 수정 안할 시 아예 담지 말기", example="5.0")
    Double place;
    @ApiModelProperty(name="위치 점수 0.0 ~ 5.0, 수정 안할 시 아예 담지 말기", example="5.0")
    Double location;
    @ApiModelProperty(name="가격 점수 0.0 ~ 5.0, 수정 안할 시 아예 담지 말기", example="5.0")
    Double price;
}
