package com.ssafy.common.handler;

import com.ssafy.api.response.BaseResponseBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/*
 * 전체 Exception을 관리하는 클래스
 *
 * @ControllerAdvice // 글로벌 적용 : 전체 Exception을 전부 관리
 *
 * Exception을 처음 다루기 때문에 상세한 Exception을 나누는 것 대신
 * 
 * CustomException과 renderDefaultPage, Exception 3가지로 처리
 *
 * CustomException을 만들어 내부에 enum 객체를 담을 수 있는 interface를 final 변수로 둠
 * 
 * 각 도메인 마다 해당 Code를 관리하는 Enum 객체를 생성하여 관리
 *
 * 각 Exception 마다 생성된 Enum을 담아서 Response를 보내주기.
 * ex) ALREADY_EXIST_NICKNAME
 */

@ControllerAdvice
public class GlobalExceptionHandler {
	@Value("${spa.default-file}")
	String defaultFile;

	@ExceptionHandler({ CustomException.class })
	protected ResponseEntity handleCustomException(CustomException ex) {
		/* 프론트에서 편리하게 다루게 하기 위해 status code는 200 고정
		 * ResponseEntity 내부의 status code를 다르게 하여 구분 짓게 함
		 */
		return ResponseEntity.status(200).body(BaseResponseBody.of(ex.getErrorCode().getCode(), ex.getErrorCode().getMessage()));
	}

	/* SPA처리를 위한 ControllerAdvice.
	 * 요청에 해당하는 Request Mapping이 존재하지 않을 경우 Default로 index.html을 렌더링한다.
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<String> renderDefaultPage(NoHandlerFoundException ex) {
		String url = ex.getRequestURL();
		if(url.startsWith("/api/")) {
			return ResponseEntity.notFound().build();
		}else {
			try {
				ClassPathResource classPathResource = new ClassPathResource(defaultFile);
				InputStream inputStream = classPathResource.getInputStream();
    				String body = StreamUtils.copyToString(inputStream, Charset.defaultCharset());
			    return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(body);
			} catch (IOException e) {
				e.printStackTrace();
				return ResponseEntity.status(200).body("{\"code\": 500, \"message\": \"There was an error completing the action.\"}");
			}
		}
	}

	@ExceptionHandler({ Exception.class })
	protected ResponseEntity handleServerException(Exception ex) {
		ex.printStackTrace();
		return ResponseEntity.status(200).body(BaseResponseBody.of(500, "Server Error."));
	}
}
