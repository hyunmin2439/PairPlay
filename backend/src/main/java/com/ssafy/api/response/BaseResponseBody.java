package com.ssafy.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 서버 요청에대한 기본 응답값(바디) 정의.
 *
 * 응답값을 따로 정의해서 쓰는 이유
 *
 * 참고 사이트 : https://tecoble.techcourse.co.kr/post/2020-08-31-dto-vs-entity/
 *
 * 1. 엔티티 내부 구현을 캡슐화 및 UI 계층에 노출시키지 않기 위해
 * 2. 화면에 필요한 데이터만 내림으로써 속도 향상
 * 3. 순환참조를 예방할 수 있다.
 * 4. validation 코드와 모델링 코드를 분리
 */
@Getter
@Setter
@ApiModel("BaseResponseBody")
public class BaseResponseBody {
	@ApiModelProperty(name="응답 메시지", example = "정상")
	String message = null;
	@ApiModelProperty(name="응답 코드", example = "200")
	Integer code = null;
	
	public BaseResponseBody() {}
	
	public BaseResponseBody(Integer code){
		this.code = code;
	}
	
	public BaseResponseBody(Integer code, String message){
		this.code = code;
		this.message = message;
	}
	
	public static BaseResponseBody of(Integer code, String message) {
		BaseResponseBody body = new BaseResponseBody();
		body.message = message;
		body.code = code;
		return body;
	}
}
