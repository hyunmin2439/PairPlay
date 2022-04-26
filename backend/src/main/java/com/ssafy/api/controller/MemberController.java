package com.ssafy.api.controller;

import com.ssafy.api.request.MemberCheckPostReq;
import com.ssafy.api.request.MemberSignupPostReq;
import com.ssafy.api.response.BaseResponseBody;
import com.ssafy.api.service.MemberService;
import com.ssafy.domain.entity.Member;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "유저 API", tags = {"Members"})
@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

//    @PostMapping("/signin")
//    @ApiOperation(value = "로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Success", response = UserLoginPostRes.class),
//            @ApiResponse(code = 401, message = "Invalid Password", response = UserLoginPostRes.class),
//    })
//    public ResponseEntity<UserLoginPostRes> login(@RequestBody @ApiParam(value = "로그인 정보", required = true) UserLoginPostReq loginInfo) {
//        String email = loginInfo.getEmail();
//        String password = loginInfo.getPassword();
//
//        // 멤버로 수정해야 함!
//        User user = memberService.getUserByEmail(email);
//        // 로그인 요청한 유저로부터 입력된 패스워드 와 디비에 저장된 유저의 암호화된 패스워드가 같은지 확인.(유효한 패스워드인지 여부 확인)
//        if (passwordEncoder.matches(password, user.getPassword())) {
//            // 유효한 패스워드가 맞는 경우, 로그인 성공으로 응답.(액세스 토큰을 포함하여 응답값 전달)
//            return ResponseEntity.ok(UserLoginPostRes.of(200, "Success", JwtTokenUtil.getToken(email)));
//        }
//        // 유효하지 않는 패스워드인 경우, 로그인 실패로 응답.
//        return ResponseEntity.status(401).body(UserLoginPostRes.of(401, "Invalid Password", null));
//    }

    @PostMapping("/signup")
    @ApiOperation(value = "회원 가입", notes = "<strong>아이디와 패스워드</strong>를 통해 회원가입 한다.")
    @ApiResponses({
//            @ApiResponse(code = 200, message = "Success", response = BaseResponseBody.class),
    })
    public ResponseEntity<? extends BaseResponseBody> signup(
            @RequestBody @ApiParam(value = "회원가입 정보", required = true) MemberSignupPostReq memberInfo) {

        Member member = memberService.signup(memberInfo);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    @PostMapping("/check/email")
    @ApiOperation(value = "이메일", notes = "<strong>이메일</strong>이 이미 존재하는지 확인한다.")
    @ApiResponses({
            @ApiResponse(code = 1, message = "Email Success", response = BaseResponseBody.class),
            @ApiResponse(code = 0, message = "Email Duplicate", response = BaseResponseBody.class),
            @ApiResponse(code = -1, message = "Value is Empty", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> checkEmail(
            @RequestBody @ApiParam(value = "이메일 중복확인 체크", required = true) MemberCheckPostReq checkInfo) {

        String email = checkInfo.getValue();

        if( "".equals(email) || email == null )
            return ResponseEntity.status(200).body(BaseResponseBody.of(-1, "Value Empty"));

        Member member = memberService.getMemberByEmail(email);

        if (member != null)
            return ResponseEntity.status(200).body(BaseResponseBody.of(0, "Email Duplicate"));

        return ResponseEntity.status(200).body(BaseResponseBody.of(1, "Email Success"));
    }

    @PostMapping("/check/nickname")
    @ApiOperation(value = "닉네임", notes = "<strong>닉네임</strong>이 이미 존재하는지 확인한다.")
    @ApiResponses({
            @ApiResponse(code = 1, message = "Nickname Success", response = BaseResponseBody.class),
            @ApiResponse(code = 0, message = "Nickname Duplicate", response = BaseResponseBody.class),
            @ApiResponse(code = -1, message = "Nickname is Empty", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> checkNickname(
            @RequestBody @ApiParam(value = "닉네임 중복확인 체크", required = true) MemberCheckPostReq checkInfo) {

        String Nickname = checkInfo.getValue();

        if( "".equals(Nickname) || Nickname == null )
            return ResponseEntity.status(200).body(BaseResponseBody.of(-1, "Nickname Empty"));

        Member member = memberService.getMemberByNickname(Nickname);

        if (member != null)
            return ResponseEntity.status(200).body(BaseResponseBody.of(0, "Nickname Duplicate"));

        return ResponseEntity.status(200).body(BaseResponseBody.of(1, "Nickname Success"));
    }

//    @GetMapping("/info")
//    @ApiOperation(value = "회원 본인 정보 조회", notes = "로그인한 회원 본인의 정보를 응답한다.")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "성공", response = UserRes.class)
//    })
//    public ResponseEntity<UserRes> getUserInfo(@ApiIgnore Authentication authentication) throws MalformedURLException {
//        /**
//         * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
//         * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
//         */
//        SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
//
//        String userId = userDetails.getUsername();
//
//        User user = memberService.getUserByEmail(userId);
//
//        return ResponseEntity.status(200).body(UserRes.of(user));
//    }
//
//    @PostMapping("/resetPassword")
//    @ApiOperation(value = "이메일 입력 받음", notes = "회원의 이메일로 임시 비밀번호 전송")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "SUCCESS", response = BaseResponseBody.class),
//            @ApiResponse(code = 404, message = "FAIL", response = BaseResponseBody.class)
//    })
//    public ResponseEntity<BaseResponseBody> resetPassword(@RequestBody @ApiParam(value = "이메일 정보", required = true) UserLoginPostReq email) {
//        String tempEmail = email.getEmail();
        // 여기서 임시 비밀번호 생성 해서 service로 넘겨줘야 함
//        System.out.println(tempEmail);
//        if (memberService.sendMail(tempEmail)) {
//            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "SUCCESS"));
//        } else {
//            return ResponseEntity.status(404).body(BaseResponseBody.of(404, "FAIL"));
//        }
//    }
}
