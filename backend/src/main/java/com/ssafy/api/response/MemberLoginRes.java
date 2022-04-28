package com.ssafy.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 요청에 따른 statusCode와 massage, accessToken을 내려줌
 */
@Getter
@Setter
@ApiModel("UserLoginPostResponse")
public class MemberLoginRes extends BaseResponseBody{
	@ApiModelProperty(name="JWT 인증 토큰", example="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN...")
	String accessToken;

	public static MemberLoginRes of(Integer statusCode, String message, String accessToken) {
		MemberLoginRes res = new MemberLoginRes();
		res.setCode(statusCode);
		res.setMessage(message);
		res.setAccessToken(accessToken);
		return res;
	}
}
