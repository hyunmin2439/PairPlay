package com.ssafy.common.handler;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logger.debug("소셜 로그인 가입을 할 수 없습니다.");

        String url = makeRedirectUrl(request.getServerName());

        getRedirectStrategy().sendRedirect(request, response, url);
    }

    private String makeRedirectUrl(String domain) {
        System.out.println(domain);

        if(domain.contains("localhost"))
            domain = "http://" + domain + ":8080";
        else
            domain = "http://pairplay.site" + ":80";

        return UriComponentsBuilder.fromUriString(domain + "/oauth/failure")
                .build().toUriString();
    }
}
