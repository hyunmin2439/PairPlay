package com.ssafy.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.api.request.MemberSignupPutReq;
import com.ssafy.api.request.ProfilePutReq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.StringTokenizer;

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

    String sido;
    String gugun;
    String detailAddress;

    String phone;
    String profileImage; // 프로필 이미지 주소
    boolean enable; // 삭제 여부
    String description;
    String socialId;

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
        set2DepthAddress( memberInfo.getAddress() );
        this.phone = memberInfo.getPhone();
    }

    // 회원 정보 수정
    public void profileUpdate(ProfilePutReq profilePutReq) {
        this.nickname = profilePutReq.getNickname();
        this.name = profilePutReq.getName();
        this.gender = profilePutReq.getGender();
        this.birthDt = profilePutReq.getBirthDt();
        set2DepthAddress( profilePutReq.getAddress() );
        this.phone = profilePutReq.getPhone();
        this.description = profilePutReq.getDescription();
    }

    public void set2DepthAddress(String address) {
        StringTokenizer st = new StringTokenizer(address);
        this.sido = st.nextToken();
        this.gugun = st.nextToken();
        StringBuffer sb = new StringBuffer();
        while(st.hasMoreTokens())
            sb.append(st.nextToken()).append(" ");
        sb.setLength(sb.length() - 1);
        this.detailAddress = sb.toString();
    }

    // Profile Image URL Update
    public void profileImageUpdate(String profileImageUrl) {
        this.profileImage = profileImageUrl;
    }

    // Password Update
    public void passwordUpdate(String password) {
        this.password = password;
    }

    // Member Enable Update
    public void memberEnableUpdate(boolean isEnable) {
        this.enable = isEnable;
    }

    public Member updateSocialId(String socialId) {
        this.socialId = socialId;
        return this;
    }
}
