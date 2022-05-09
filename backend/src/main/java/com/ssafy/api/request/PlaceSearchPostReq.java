package com.ssafy.api.request;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@ApiModel("PlaceSearchPostRequest")
public class PlaceSearchPostReq {
    @ApiModelProperty(name="시/도", example="서울")
    String sido;
    @ApiModelProperty(name="시/구/군", example="강남구")
    String gugun;
    @ApiModelProperty(name="운동종목", example="[\"축구\", \"야구\", \"농구\"]")
    List<String> categoryList;
    @ApiModelProperty(name="찾는 기간", example="2022-05-01")
    LocalDate startDate;
    @ApiModelProperty(name="찾는 기간", example="2022-05-03")
    LocalDate endDate;
    @ApiModelProperty(name="검색 단어", example="체육관")
    String searchWord;
//    @ApiModelProperty(name="최소 가격", example="10000")
//    int minPrice;
//    @ApiModelProperty(name="최대 가격", example="100000")
//    int maxPrice;
}
