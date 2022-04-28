package com.ssafy.api.service;

import com.ssafy.api.request.MemberSignupPostReq;
import com.ssafy.api.request.MemberSignupPutReq;
import com.ssafy.common.handler.CustomException;
import com.ssafy.domain.entity.Member;
import com.ssafy.domain.repository.MemberRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static com.ssafy.common.statuscode.CommonCode.EMPTY_REQUEST_VALUE;
import static com.ssafy.common.statuscode.MemberCode.FAIL_ALREADY_EXIST_EMAIL;
import static com.ssafy.common.statuscode.MemberCode.FAIL_ALREADY_EXIST_NICKNAME;

@Service("memberService")
public class MemberService {

    private final MemberRepository memberRepository;

//    private final JavaMailSender javaMailSender;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /**
     * memberId를 통해 DB에서 유저 정보 조회
     */
    public Member getMemberById(Long memberId) {
        return memberRepository.findById(memberId).orElse(null);
    }

    /**
     * memberEmail을 통해 DB에서 유저 정보 조회
     */
    public Member getMemberByEmail(String memberEmail) {
        return memberRepository.findByEmail(memberEmail).orElse(null);
    }

    /**
     * memberNickname을 통해 DB에서 유저 정보 조회
     */
    public Member getMemberByNickname(String memberNickname) {
        return memberRepository.findByNickname(memberNickname).orElse(null);
    }

    /**
     * email 중복 체크
     */
    public void checkEmail(String email) {
        if( "".equals(email) || email == null )
            throw new CustomException(EMPTY_REQUEST_VALUE);

        Member member = getMemberByEmail(email);

        if( member != null )
            throw new CustomException(FAIL_ALREADY_EXIST_EMAIL);
    }

    /**
     * nickname 중복 체크
     */
    public void checkNickname(String nickname) {
        if( "".equals(nickname) || nickname == null )
            throw new CustomException(EMPTY_REQUEST_VALUE);

        Member member = getMemberByNickname(nickname);

        if (member != null)
            throw new CustomException(FAIL_ALREADY_EXIST_NICKNAME);
    }

    /**
     * 회원 가입
     */
    public Member signup(MemberSignupPostReq memberInfo) {
        String email = memberInfo.getEmail();
        String nickname = memberInfo.getNickname();

        checkEmail(email);
        checkNickname(nickname);

        Member member = Member.builder()
                .email(email)
                .nickname(nickname)
                .password(memberInfo.getPassword())
                .build();

        return memberRepository.save(member);
    }

    /**
     * 추가 정보 등록
     */
    public Member afterSignup(MemberSignupPutReq memberInfo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());

        Member member = getMemberById(memberId);
        member.afterSignup(memberInfo);
        return memberRepository.save(member);
    }

//    @Override
//    public boolean sendMail(String email) {
//        // 아이디로 메일, 비밀번호 가져오기
//        User user = this.getUserByEmail(email);
//
//        System.out.println(user);
//
//        // 메일 보내기
//        if (user != null) {
//            // 메세지 내용이 너무 심플함. 좀 더 제목이랑 내용 추가 시키면 좋을 듯
//            SimpleMailMessage simpleMessage = new SimpleMailMessage();
//            simpleMessage.setTo(user.getEmail());
//            simpleMessage.setSubject(user.getNickname() + "님의 임시 비밀번호");
//            String tmpPassword = PasswordUtil.getRandomPassword(); // 임시비밀번호
//            String tmpJwtToken = passwordEncoder.encode(tmpPassword); // 임시비밀번호의 jwtToken // 임시비밀번호 생성 암호화 컨트롤러에서 아니면 패스워드 유틸에서
//            // password update 구문
//            user.resetPassword(tmpJwtToken);
//            memberRepository.save(user);
//            simpleMessage.setText(user.getNickname() + "님의 임시비밀번호는 " + tmpPassword + " 입니다.");
//            javaMailSender.send(simpleMessage);
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    @Override
//    public void deleteUserById(Long memberId) {
//        Member member = getMemberById(memberId);
//
//        memberRepository.delete(member);
//    }
}
