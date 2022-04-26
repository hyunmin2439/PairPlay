package com.ssafy.config;

import com.ssafy.common.util.JwtTokenUtil;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

/*
 * ~ com\ssafy\config\WebMvcConfig.java uses unchecked or unsafe operations.
 * Recompile with -Xlint:unchecked for details.
 *
 * Generic 클래스나 인터페이스를 선언 및 생성할 때 자료형 선언 안해서 나는 에러
 * 실행 시 정상 동작하므로 큰 신경 안써도 됨.
 */

@Configuration
@SuppressWarnings("unchecked") // 위의 나는 에러 안보이게 처리
public class WebMvcConfig implements WebMvcConfigurer {
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // configuration.addAllowedOrigin("*");
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.addExposedHeader(JwtTokenUtil.HEADER_STRING);
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    		registry.addResourceHandler("/resources/**")
    				.addResourceLocations("/WEB-INF/resources/");
    		
//    		registry.addResourceHandler("/swagger-ui/index.html/")
//    				.addResourceLocations("classpath:/META-INF/resources/");

    		registry.addResourceHandler("/webjars/**")
    				.addResourceLocations("classpath:/META-INF/resources/webjars/");
    		
    		/*
    		 * 
    		 * Front-end에서 참조하는 URL을 /dist로 매핑
    		 * 
    		 */
//        registry.addResourceHandler("/css/**")
//        			.addResourceLocations("classpath:/dist/css/");
//        	registry.addResourceHandler("/fonts/**")
//        			.addResourceLocations("classpath:/dist/fonts/");
//        registry.addResourceHandler("/icons/**")
//				.addResourceLocations("classpath:/dist/icons/");
//        registry.addResourceHandler("/img/**")
//			.addResourceLocations("classpath:/dist/img/");
//        registry.addResourceHandler("/js/**")
//				.addResourceLocations("classpath:/dist/js/");
//        registry.addResourceHandler("/profile/image/**")
//                .addResourceLocations("classpath:/static/profile_img/**");
    }

    public Filter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setIncludeHeaders(true);
        loggingFilter.setMaxPayloadLength(64000);
        return loggingFilter;
    }

    @Bean
    public FilterRegistrationBean loggingFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(requestLoggingFilter());
        registration.addUrlPatterns("/api/v1/*");
        return registration;
    }
}
