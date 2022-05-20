package com.ssafy.api.request;

import com.ssafy.domain.entity.Activity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@ApiModel("ActivityRegisterReq")
public class ActivityRegisterReq {
    @ApiModelProperty(name = "메이트 신청 공고 ID")
    Long activityId;

}
