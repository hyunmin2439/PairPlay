package com.ssafy.common.statuscode;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ActivityCode implements StatusCode{

    //200 Success 요청 성공
    SUCCESS_MATE_LIST(200, "메이트 리스트 조회 성공했습니다."),
    ;


    protected final int code;
    protected final String message;

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
