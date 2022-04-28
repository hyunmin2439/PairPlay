package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ApiModel("MatePostReq")
public class ActivityPostReq {

    @ApiModelProperty(name = "주소")
    String location;


    @ApiModelProperty(name = "카테고리ID")
    Long categoryId;

    @ApiModelProperty(name = "팀/개인 구분")
    boolean isTeam;


    @ApiModelProperty(name = "등록자 ID")
    long createId;//아마 JWT에서 꺼내야 할 것 같은데 추후 수정


    @ApiModelProperty(name = "모임 날짜")
    LocalDateTime meetDt;


    @ApiModelProperty(name = "제목")
    String title;

    @ApiModelProperty(name = "내용")
    String description;



}
