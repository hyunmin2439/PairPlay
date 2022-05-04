package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@ApiModel("MateCategoryReq")
public class ActivityCategoryReq {

    @ApiModelProperty(name = "시도")
    String sido;

    @ApiModelProperty(name = "군구")
    String gungu;
    
    @ApiModelProperty(name = "카테고리ID")
    Long categoryId;

    @ApiModelProperty(name = "검색 내용")
    String search;

}
