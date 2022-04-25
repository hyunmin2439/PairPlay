package com.ssafy.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Entity;
import java.time.LocalDate;

/**
 * 유저 모델 정의.
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Member extends BaseEntity {
    String email;
    String nickname;
    String name;
    int gender; // 0(남) / 1(여) Converter 사용할지 고민
    LocalDate birthDt;
    String address;
    String phone;
    String profileImage; // 프로필 이미지 주소
    boolean enable; // 삭제 여부
    
    // Jackson 라이브러리 Annotation
    @JsonIgnoreProperties// 직렬화 시 제외 필드
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // 쓰기 전용
    String password;
}
