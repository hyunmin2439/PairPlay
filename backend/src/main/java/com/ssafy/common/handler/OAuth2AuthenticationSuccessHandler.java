package com.ssafy.common.handler;

import com.ssafy.common.util.JwtTokenUtil;
import com.ssafy.domain.entity.Member;
import com.ssafy.domain.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        // socialId
        String userSocialId = authentication.getName();

        Member member = memberRepository.findBySocialId(userSocialId).orElse(null);
        int isLogin = 0;

        System.out.println(ChronoUnit.MINUTES.between(member.getCreatedDate(), LocalDateTime.now()));

        // 유저가 존재하면
        if(member != null) {
            // google에서 사용하는 userId를 비밀번호로
            member.resetPassword(new BCryptPasswordEncoder().encode(userSocialId));

            isLogin = (ChronoUnit.MINUTES.between(member.getCreatedDate(), LocalDateTime.now()) >= 1) ? 1 : 0;

            memberRepository.save(member);
        }

//        String targetUrl = determineTargetUrl(request, response, authentication);

        String url = makeRedirectUrl(
                request.getServerName(),
                JwtTokenUtil.getToken(String.valueOf(member.getId())),
                member.getId(),
                isLogin
        );

        if (response.isCommitted()) {
            logger.debug("응답이 이미 커밋된 상태입니다. " + url + "로 리다이렉트하도록 바꿀 수 없습니다.");
            return;
        }

        getRedirectStrategy().sendRedirect(request, response, url);
    }

    private String makeRedirectUrl(String domain, String token, Long memberId, int isLogin) {
        System.out.println(domain);

        if(domain.contains("localhost"))
            domain = "http://" + domain + ":8080";
        else
            domain = "http://pairplay.site" + ":80";

        return UriComponentsBuilder.fromUriString(domain + "/oauth/success")
                .queryParam("accessToken", token)
                .queryParam("memberId", memberId)
                .queryParam("isLogin", isLogin)
                .build().toUriString();

//        return UriComponentsBuilder.fromUriString("https://k6e205.p.ssafy.io:443/oauth/success")
//                .queryParam("accessToken", token)
//                .build().toUriString();
    }
}
