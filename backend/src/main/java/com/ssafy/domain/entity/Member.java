package com.ssafy.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.api.request.MemberSignupPutReq;
import com.ssafy.api.request.ProfilePutReq;
import com.ssafy.common.util.AddressUtil;
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
    private String email;
    private String nickname;
    private String name;
    private int gender; // 0(남) / 1(여) Converter 사용할지 고민
    private LocalDate birthDt;

    private String sido;
    private String gugun;
    private String detailAddress;

    private String phone;
    private String profileImage; // 프로필 이미지 주소
    private boolean enable; // 삭제 여부
    private String description;
    private String socialId;

    // Jackson 라이브러리 Annotation
    @JsonIgnoreProperties// 직렬화 시 제외 필드
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // 쓰기 전용
    private String password;

    // 패스워드 랜덤 비밀번호로 초기화
    public void resetPassword(String password) {
        this.password = password;
    }

    // 회원 가입 후 추가 정보 등록
    public void afterSignup(MemberSignupPutReq memberInfo) {
        this.name = memberInfo.getName();
        this.gender = memberInfo.getGender();
        this.birthDt = memberInfo.getBirthDt();
        set3DepthAddress( memberInfo.getAddress() );
        this.phone = memberInfo.getPhone();
    }

    // 회원 정보 수정
    public void profileUpdate(ProfilePutReq profilePutReq) {
        this.nickname = profilePutReq.getNickname();
        this.name = profilePutReq.getName();
        this.gender = profilePutReq.getGender();
        this.birthDt = profilePutReq.getBirthDt();
        set3DepthAddress( profilePutReq.getAddress() );
        this.phone = profilePutReq.getPhone();
        this.description = profilePutReq.getDescription();
    }

    // 주소 체계 저장
    public void set3DepthAddress(String address) {
        StringTokenizer st = new StringTokenizer(address);

        String[] splitAddress = AddressUtil.get3DepthAddress(address);
        this.sido = splitAddress[0];
        this.gugun = splitAddress[1];
        this.detailAddress = splitAddress[2];

    }

    // 주소 가져올 때
    public String getAddress() {
        return this.sido + " " + this.gugun + " " + this.detailAddress;
    }

    // Profile Image URL Update
    public void profileImageUpdate(String profileImage) {
        this.profileImage = profileImage;
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
