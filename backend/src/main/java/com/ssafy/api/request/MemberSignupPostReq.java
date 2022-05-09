package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("MemberSignupPostRequest") // 명칭이 같은 것이 존재할 경우 Swagger에서 구분을 못함
public class MemberSignupPostReq extends MemberLoginPostReq{
    @ApiModelProperty(name="닉네임", example="ssafy1234")
    String nickname;
}
