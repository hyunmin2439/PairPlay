package com.ssafy.common.statuscode;

import io.swagger.annotations.Contact;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MemberCode implements StatusCode{
    // 200 Success 요청 성공
    SUCCESS_SIGN_UP(200, "회원가입에 성공했습니다."),
    SUCCESS_EMAIL_NOT_FOUND(200, "등록 가능한 이메일입니다."),
    SUCCESS_NICKNAME_NOT_FOUND(200, "등록 가능한 닉네임입니다."),

    // 409 CONFLICT 중복된 리소스
    FAIL_ALREADY_EXIST_EMAIL(409, "이미 존재하는 이메일입니다."),
    FAIL_ALREADY_EXIST_NICKNAME(409, "이미 존재하는 닉네임입니다.");

    protected final int code;
    protected final String message;

    @Override
    public int getCode() { return this.code; }

    @Override
    public String getMessage() { return message; }
}
