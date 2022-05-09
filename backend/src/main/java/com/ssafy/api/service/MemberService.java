package com.ssafy.api.service;

import com.ssafy.api.request.MemberSignupPostReq;
import com.ssafy.api.request.MemberSignupPutReq;
import com.ssafy.common.handler.CustomException;
import com.ssafy.domain.document.PlaceMember;
import com.ssafy.domain.entity.Member;
import com.ssafy.domain.repository.MemberRepository;
import com.ssafy.domain.repository.PlaceMemberRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

import static com.ssafy.common.statuscode.CommonCode.EMPTY_REQUEST_VALUE;
import static com.ssafy.common.statuscode.MemberCode.*;

@Service("memberService")
public class MemberService {

    private final MemberRepository memberRepository;
    private final PlaceMemberRepository placeMemberRepository;

    private final JavaMailSender javaMailSender;

    public MemberService(MemberRepository memberRepository, JavaMailSender javaMailSender, PlaceMemberRepository placeMemberRepository) {
        this.memberRepository = memberRepository;
        this.javaMailSender = javaMailSender;
        this.placeMemberRepository = placeMemberRepository;
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
     * 인증 정보에서 memberId값을 가져와 MysqlDB에서 유저 정보를 검색
     */
    public Member getMemberFromAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());
        return getMemberById(memberId);
    }

    /**+
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
                .enable(true)
                .build();

        memberRepository.save(member);

        // MongoDB에 유저
        PlaceMember placeMember = PlaceMember.builder()
                .memberId( member.getId() )
                .likeItems( new LinkedList<>() )
                .recentItems( new LinkedList<>() )
                .build();

        placeMemberRepository.save(placeMember);

        return member;
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

    /**
     * 비밀번호 초기화
     */
    public void sendMail(String email, String tmpPassword, String encrypted) {
        if( "".equals(email) || email == null )
            throw new CustomException(EMPTY_REQUEST_VALUE);

        // 아이디로 메일, 비밀번호 가져오기
        Member member = getMemberByEmail(email);

        // 메일로 유저가 조회가 안되면
        if (member == null)
            throw new CustomException(FAIL_MEMBER_NOT_FOUND);

        // 메세지 내용이 너무 심플함. 좀 더 제목이랑 내용 추가 시키면 좋을 듯
        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        simpleMessage.setTo(member.getEmail());
        simpleMessage.setSubject(member.getNickname() + "님의 임시 비밀번호");
        simpleMessage.setText(member.getNickname() + "님의 임시비밀번호는 " + tmpPassword + " 입니다.");

        // 메일 전송
        javaMailSender.send(simpleMessage);

        // password update 구문
        member.resetPassword(encrypted);
        memberRepository.save(member);
    }

}
