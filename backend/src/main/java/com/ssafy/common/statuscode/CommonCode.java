package com.ssafy.common.statuscode;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CommonCode implements StatusCode {
    // 200 기본 Success
    SUCCESS_REQUEST(200, "요청을 성공적으로 수행했습니다."),

    //400 BAD_REQUEST 잘못된 요청
    EMPTY_REQUEST_VALUE(400, "요청 변수 값이 비어 있습니다."),

    //404 NOT_FOUND 잘못된 리소스 접근

    //409 CONFLICT 중복된 리소스

    //500 INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(500, "서버 에러입니다. 서버 팀에 연락주세요!");

    protected final int code;
    protected final String message;

    @Override
    public int getCode() { return this.code; }

    @Override
    public String getMessage() { return message; }
}
