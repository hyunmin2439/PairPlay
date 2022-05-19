package com.ssafy.config;

import com.fasterxml.classmate.TypeResolver;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * API 문서 관련 swagger2 설정 정의.
 *
 *
 * SpringBoot 2.6버전 Swagger 3.0 버전 적용시 에러
 *
 * 에러 1
 * Failed to start bean 'documentationPluginsBootstrapper'; nested exception is java.lang.NullPointerException
 *
 * @EnableWebMvc
 * 위 어노테이션을 적용하면 해결된다는 말이 있으나 이것으로 해결 불가
 * 위 어노테이션 적용시 WebMvcConfigurer 인터페이스를 구현한 클래스 안에 ResourceHandler를 Override한 것이 동작하지가 않는다.
 *
 * 따라서, registry.addResourceHandler("/swagger-ui/index.html/").addResourceLocations("classpath:/META-INF/resources/");
 * - 참고 사이트 : https://maivve.tistory.com/305
 * 메서드가 정상 동작 하지 않아서 Swagger에 접속할 수 없다.
 *
 * Swagger 구현 클래스에 extends WebMvcConfigurationSupport를 추가하고 addResourceHandlers를 Override하면 정상 동작
 *
 *
 * 에러 2
 * No primary or single unique constructor found for interface org.springframework.data.domain.Pageable
 *
 * 1. WebMvcConfigurationSupport Extends 받아 addResourceHandlers를 오버라이드할 경우
 *
 * 2. WebAutoConfiguration이 MVC를 구성해 주지 않는다.
 *
 * 3. Pageable을 자동으로 init해주는 리졸버는 PageableHandlerMethodArgumentResolver
 *
 * 4. PageableHandlerMethodArgumentResolver는 WebAutoConfiguration에서 추가 해주기 때문에 기본 생성자가 없다는 것
 *
 * 즉, 현재 상황은 Swagger 때문에 WebMvcConfigurationSupport extends가 강제되는 상황
 *
 * 직접, PageableHandlerMethodArgumentResolver를 추가해주는 방법
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

    // Pageable 설정 추가
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add( new PageableHandlerMethodArgumentResolver());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui/**").addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
    }

    TypeResolver typeResolver = new TypeResolver();

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(Pageable.class),typeResolver.resolve(Page.class)))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/**"))
                .build()
                .securityContexts(newArrayList(securityContext()))
                .securitySchemes(newArrayList(apiKey()))
                ;
    }

    private ApiKey apiKey() {
        return new ApiKey(SECURITY_SCHEMA_NAME, "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    public static final String SECURITY_SCHEMA_NAME = "JWT";
    public static final String AUTHORIZATION_SCOPE_GLOBAL = "global";
    public static final String AUTHORIZATION_SCOPE_GLOBAL_DESC = "accessEverything";

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope(AUTHORIZATION_SCOPE_GLOBAL, AUTHORIZATION_SCOPE_GLOBAL_DESC);
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return newArrayList(new SecurityReference(SECURITY_SCHEMA_NAME, authorizationScopes));
    }

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                // try it 기능 활성화 범위, 만약 try it 기능이 안된다면 아래 부분 있는지 확인
//                .supportedSubmitMethods(newArrayList("get").toArray(new String[0]))
//                .operationsSorter(METHOD)
                .build();
    }

    @Getter
    @Setter
    @ApiModel
    static class Page {
        @ApiModelProperty(value = "페이지 번호(0..N)")
        private Integer page;

        @ApiModelProperty(value = "페이지 크기", allowableValues = "range[0, 100]")
        private Integer size;

        @ApiModelProperty(value = "정렬(사용법: 컬럼명,ASC|DESC)")
        private List<String> sort;
    }


}