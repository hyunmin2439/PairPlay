package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel("PlaceReviewDeleteRequest")
public class PlaceReviewDeleteReq {
    @ApiModelProperty(name="삭제할 리뷰 체육 시설 아이디", example="김싸피")
    Long placeId;
}
