package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ApiModel("MemberCheckPostRequest")
public class MemberCheckPostReq {
    @ApiModelProperty(name="이메일 or 닉네임", example="email or nickname")
    String value;
}
