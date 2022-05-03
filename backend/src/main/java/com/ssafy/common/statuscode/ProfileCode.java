package com.ssafy.common.statuscode;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ProfileCode implements StatusCode{
    // 200 Success 요청 성공
    SUCCESS_SEARCH_PROFILE(200, "유저 프로필 조회에 성공했습니다."),
    SUCCESS_UPDATE_PROFILE(200, "유저 프로필 수정에 성공했습니다."),
    SUCCESS_UPDATE_PROFILE_IMAGE(200, "유저 프로필 이미지 수정에 성공했습니다."),
    SUCCESS_UPDATE_PASSWORD(200, "비밀번호 변경에 성공했습니다."),
    SUCCESS_WITHDRAW_MEMBER(200, "회원 비활성화에 성공했습니다."),
    SUCCESS_SEARCH_CALENDAR(200, "달력 조회에 성공했습니다."),
    SUCCESS_SEARCH_CALENDAR_DATE(200, "개인활동 조회에 성공했습니다."),

    // 404 NOT_FOUND 잘못된 리소스 접근
    FAIL_MEMBER_NOT_FOUND(404, "해당 유저 정보를 찾지 못했습니다.");

    protected final int code;
    protected final String message;

    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
