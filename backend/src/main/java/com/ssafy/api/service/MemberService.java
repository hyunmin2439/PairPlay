package com.ssafy.api.service;

import com.ssafy.common.util.PasswordUtil;
import com.ssafy.domain.entity.Member;
import com.ssafy.domain.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MemberService {
    @Autowired
    MemberRepository memberRepository;
//    @Autowired
//    JavaMailSender javaMailSender;

    @Autowired
    PasswordEncoder passwordEncoder;

//    @Override
//    public Member createUser(UserRegisterPostReq userRegisterInfo) {
//
//        String nickname = userRegisterInfo.getNickname();
//        String email = userRegisterInfo.getEmail();
//        String password = passwordEncoder.encode(userRegisterInfo.getPassword());
//
//        Member user = User.builder()
//                .email(email)
//                .nickname(nickname)
//                .password(password)
//                .role("USER")
//                .maskId(1)
//                .maskBack(1)
//                .socialId("0")
//                .profileImg("default-profile-image.jpg")
//                .build();
//        return memberRepository.save(user);
//    }
//
    public Member getMemberById(Long memberId) {
        // 디비에 유저 정보 조회 (userEmail 를 통한 조회).
        Member member = memberRepository.findById(memberId).orElse(null);

        System.out.println(member); // 추가
        return member;
    }

    public Member getMemberByEmail(String memberEmail) {
        // 디비에 유저 정보 조회 (userEmail 를 통한 조회).
        Member member = memberRepository.findByEmail(memberEmail).orElse(null);

        System.out.println(member); // 추가
        return member;
    }

//    @Override
//    public User getUserByNickname(String userNickname) {
//
//        User user = userRepositorySupport.findByNickname(userNickname).orElse(null);
//        return user;
//    }
//
//    @Override
//    public User getUserById(Long userId) {
//        User user = userRepositorySupport.findUserById(userId).orElse(null);
//        return user;
//    }
//
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
//            String tmpJwtToken = passwordEncoder.encode(tmpPassword); // 임시비밀번호의 jwtToken
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
