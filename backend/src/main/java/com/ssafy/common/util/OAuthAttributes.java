package com.ssafy.common.util;

import com.ssafy.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String nickname;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes,
                           String nameAttributeKey, String nickname,
                           String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey= nameAttributeKey;
        this.nickname = nickname;
        this.email = email;
        this.picture = picture;
    }

    // OAuth2User에서 반환하는 사용자 정보는 Map이기 때문에 값 하나하나를 변환해야 한다.
    public static OAuthAttributes of(String registrationId,
                                     String userNameAttributeName,
                                     Map<String, Object> attributes) {
        switch (registrationId) {
            case "kakao": return ofKaKao(userNameAttributeName, attributes);
            default: return ofGoogle(userNameAttributeName, attributes);
        }
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName,
                                            Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .nickname((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofKaKao(String userNameAttributeName,
                                           Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

//        System.out.println(attributes);
//        System.out.println(profile.get("nickname"));
//        System.out.println(kakaoAccount.get("email"));
//        System.out.println(profile.get("profile_image_url"));

        return OAuthAttributes.builder()
                .nickname((String) profile.get("nickname"))
                .email((String) kakaoAccount.get("email"))
                .picture((String) profile.get("profile_image_url"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    // 엔티티 생성하는 시점은 처음 가입할 때
    public Member toEntity(String socialId, String nickname) {
        this.nickname = nickname;

        return Member.builder()
                .nickname(nickname)
                .email(email)
                .socialId(socialId)
                .profileImage(picture != "" ? picture : "default_profile.jpeg")
                .name(nickname)
                .sido("부산")
                .gugun("강서구")
                .detailAddress("녹산산업중로 333")
                .birthDt(LocalDateTime.now().plusHours(9).toLocalDate())
                .phone("010-0000-0000")
                .enable(true)
                .build();
    }

}
