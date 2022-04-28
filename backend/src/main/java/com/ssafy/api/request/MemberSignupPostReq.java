package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("MemberSignupPostRequest")
public class MemberSignupPostReq extends MemberLoginPostReq{
    @ApiModelProperty(name="닉네임", example="ssafy1234")
    String nickname;
}
