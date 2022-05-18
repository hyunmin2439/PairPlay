package com.ssafy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
//(exclude={MultipartAutoConfiguration.class})
@SpringBootApplication
@EnableScheduling
public class PairplayApplication {

	/**
	 * Caused by: java.net.SocketException: Network is unreachable: connect
	 *
	 * 위 에러를 해결하기 위한 코드
	 * EC2의 메타데이터를 읽다가 발생하는 에러로써, EC2인스턴스가 아닌 곳에서는 의미가 없는 에러
	 * Local환경에서 테스트 시 발생하는 에러로 Local 테스트 시 불편함을 해소하기 위한 코드
	 *
	 * 상세한 내용 하단 링크 참조
	 * https://lemontia.tistory.com/1006
	 */
	static {
		try {
			if(InetAddress.getLocalHost().getHostName().contains("DESKTOP"))
				System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(PairplayApplication.class, args);
	}

	@Bean
	public HttpMessageConverter<String> responseBodyConverter() {
		return new StringHttpMessageConverter(StandardCharsets.UTF_8);
	}

	@Bean
	public CharacterEncodingFilter characterEncodingFilter() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}
}
