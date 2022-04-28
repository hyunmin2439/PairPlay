package com.ssafy.common.auth;

import com.ssafy.api.service.MemberService;
import com.ssafy.domain.entity.Member;
import com.ssafy.domain.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


/**
 * 현재 액세스 토큰으로 부터 인증된 유저의 상세정보(활성화 여부, 만료, 롤 등) 관련 서비스 정의.
 */
@Component
public class PairplayUserDetailService implements UserDetailsService{

	@Autowired
	MemberRepository memberRepository;
	
    @Override
    public UserDetails loadUserByUsername(String memberEmail) throws UsernameNotFoundException {

    		Member member = memberRepository.findByEmail(memberEmail).orElse(null);
    		if(member != null) {
    			PairplayUserDetails userDetails = new PairplayUserDetails(member);
    			return userDetails;
    		}
    		return null;
    }
}
