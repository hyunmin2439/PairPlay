package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("MemberSignupPostRequest")
public class MemberSignupPostReq {
    @ApiModelProperty(name="이메일", example="ssafy@ssafy.com")
    String email;
    @ApiModelProperty(name="닉네임", example="ssafy1234")
    String nickname;
    @ApiModelProperty(name="비밀번호", example="!ssafy1234")
    String password;
}
