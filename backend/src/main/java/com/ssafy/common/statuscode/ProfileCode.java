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
    FAIL_PROFILE_IMAGE_S3_UPLOAD_ERROR(404, "프로필 이미지를 S3서버에 업로드하지 못했습니다."),
    FAIL_MEMBER_NOT_FOUND(404, "해당 유저 정보를 찾지 못했습니다."),

    // 409 CONFLICT 중복된 리소스
    FAIL_DUPLICATE_PASSWORD(409, "현재 비밀번호와 동일한 비밀번호 입니다.");

    protected final int code;
    protected final String message;

    @Override
    public int getCode() { return this.code; }

    @Override
    public String getMessage() { return message; }
}