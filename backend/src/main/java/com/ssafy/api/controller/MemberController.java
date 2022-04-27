package com.ssafy.api.controller;

import com.ssafy.api.request.MemberCheckPostReq;
import com.ssafy.api.request.MemberSignupPostReq;
import com.ssafy.api.request.MemberSignupPutReq;
import com.ssafy.api.response.BaseResponseBody;
import com.ssafy.api.service.MemberService;
import com.ssafy.common.handler.CustomException;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ssafy.common.statuscode.CommonCode.EMPTY_REQUEST_VALUE;
import static com.ssafy.common.statuscode.MemberCode.*;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "유저 API", tags = {"Members"})
@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    // 이렇게 쓰더라도 Swagger ApiResponse에 적용 안됨.
//    private static final int SUCCESS_EMAIL_NOT_FOUND_statusCode = SUCCESS_EMAIL_NOT_FOUND.getStatusCode();
//    private static final String SUCCESS_EMAIL_NOT_FOUND_message = SUCCESS_EMAIL_NOT_FOUND.getMessage();

    /**
     * @Autowired 필드 주입 방식 대신 스프링 팀에서 권장하는 생성자 주입 방식 사용
     *
     * 장점
     * 1. 순환 참조를 방지할 수 있다
     * 2. 테스트 코드 작성이 편리하다.
     * 3. 조금 더 품질 좋은 코드를 만들 수 있다.
     * 4. immutable 하다.
     * 5. 오류를 사전에 방지할 수 있다.
     */
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    public MemberController(MemberService memberService, PasswordEncoder passwordEncoder) {
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/check/email")
    @ApiOperation(value = "이메일 중복확인", notes = "<strong>이메일</strong>이 이미 존재하는지 확인한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "등록 가능한 이메일입니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 400, message = "요청 변수 값이 비어 있습니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 409, message = "이미 존재하는 이메일입니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "Server Error.", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> checkEmail(
            @RequestBody @ApiParam(value = "이메일", required = true) MemberCheckPostReq checkInfo) {

        memberService.checkEmail(checkInfo.getValue());
        return ResponseEntity.status(200).body(BaseResponseBody.of(SUCCESS_EMAIL_NOT_FOUND.getCode(), SUCCESS_EMAIL_NOT_FOUND.getMessage()));
    }

    @PostMapping("/check/nickname")
    @ApiOperation(value = "닉네임 중복확인", notes = "<strong>닉네임</strong>이 이미 존재하는지 확인한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "등록 가능한 닉네임입니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 400, message = "요청 변수 값이 비어 있습니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 409, message = "이미 존재하는 닉네임입니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "Server Error.", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> checkNickname(
            @RequestBody @ApiParam(value = "닉네임", required = true) MemberCheckPostReq checkInfo) {

        memberService.checkNickname(checkInfo.getValue());
        return ResponseEntity.status(200).body(BaseResponseBody.of(SUCCESS_NICKNAME_NOT_FOUND.getCode(), SUCCESS_NICKNAME_NOT_FOUND.getMessage()));
    }

    @PostMapping("/signup")
    @ApiOperation(value = "회원 가입", notes = "<strong>아이디와 패스워드, 닉네임</strong>을 입력하여 회원가입 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원가입에 성공했습니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 400, message = "요청 변수 값이 비어 있습니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 409, message = "이미 존재하는 이메일입니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 409, message = "이미 존재하는 닉네임입니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "Server Error.", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> signup(
            @RequestBody @ApiParam(value = "회원가입 정보", required = true) MemberSignupPostReq memberInfo) {

        // 순환 참조 문제 해결 때문에 컨트롤러에서 암호화해서 넣기
        String password = memberInfo.getPassword();

        if( "".equals(password) || password == null )
            throw new CustomException(EMPTY_REQUEST_VALUE);

        memberInfo.setPassword( passwordEncoder.encode(password) );
        memberService.signup(memberInfo);
        return ResponseEntity.status(200).body(BaseResponseBody.of(SUCCESS_SIGN_UP.getCode(), SUCCESS_SIGN_UP.getMessage()));
    }

    @PostMapping("/signup")
    @ApiOperation(value = "2차 회원가입", notes = "<strong>유저 추가 정보</strong>를 등록한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원가입에 성공했습니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "Server Error.", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> afterSignup(
            @RequestBody @ApiParam(value = "회원가입 정보", required = true) MemberSignupPutReq memberInfo) {

        // 수정
//        memberService.signup(memberInfo);
        return ResponseEntity.status(200).body(BaseResponseBody.of(SUCCESS_SIGN_UP.getCode(), SUCCESS_SIGN_UP.getMessage()));
    }

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
