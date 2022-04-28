package com.ssafy.common.statuscode;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MemberCode implements StatusCode{
    // 200 Success 요청 성공
    SUCCESS_AFTER_SIGN_UP(200, "추가 정보 등록에 성공했습니다."),
    SUCCESS_EMAIL_NOT_FOUND(200, "등록 가능한 이메일입니다."),
    SUCCESS_NICKNAME_NOT_FOUND(200, "등록 가능한 닉네임입니다."),
    SUCCESS_SIGN_UP(200, "회원가입에 성공했습니다."),
    SUCCESS_SIGN_IN(200, "로그인에 성공했습니다."),
    SUCCESS_RESET_PASSWORD(200, "비밀번호 초기화에 성공했습니다."),

    // 401 Access Dinied 접근 권한 없음
    FAIL_INVALID_EMAIL(401, "유효한 이메일이 아닙니다."),
    FAIL_INVALID_PASSWORD(401, "유효한 비밀번호가 아닙니다."),

    // 404 NOT_FOUND 잘못된 리소스 접근
    FAIL_MEMBER_NOT_FOUND(404, "해당 유저 정보를 찾지 못했습니다."),

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
