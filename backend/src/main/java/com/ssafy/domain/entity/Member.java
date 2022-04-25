package com.ssafy.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.api.request.MemberSignupPutReq;
import lombok.*;

import javax.persistence.Entity;
import java.time.LocalDate;

/**
 * 유저 모델 정의.
 */
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

    // 패스워드 랜덤 비밀번호로 초기화
    public void resetPassword(String password) {
        this.password = password;
    }

    // 회원 가입 후 추가 정보 등록
    public void afterSignup(MemberSignupPutReq memberInfo) {
        this.name = memberInfo.getName();
        this.gender = memberInfo.getGender();
        this.birthDt = memberInfo.getBirthDt();
        this.address = memberInfo.getAddress();
        this.phone = memberInfo.getPhone();
    }

}
