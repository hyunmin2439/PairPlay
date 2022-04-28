package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ProfilePasswordPostReq")
public class ProfilePasswordPostReq {
    @ApiModelProperty(name="비밀번호", example="!ssafy1234")
    String password;
}
