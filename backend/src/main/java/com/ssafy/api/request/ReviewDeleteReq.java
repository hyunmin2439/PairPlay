package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel("ReviewDeleteRequest")
public class ReviewDeleteReq {
    @ApiModelProperty(name="삭제할 리뷰 id", example="62722d8f5c1e32cfbf8eb719")
    String reviewId;
}
