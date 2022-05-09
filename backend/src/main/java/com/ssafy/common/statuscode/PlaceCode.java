package com.ssafy.common.statuscode;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PlaceCode implements StatusCode {
    // 200 Success 요청 성공
    SUCCESS_DETAIL_PLACE(200, "체육 시설 상세 조회에 성공했습니다."),
    SUCCESS_SEARCH_PLACE(200, "체육 시설 목록 검색에 성공했습니다."),
    SUCCESS_UPDATE_LIKE_PLACE(200, "찜 등록/삭제에 성공했습니다.");

    // 401 Access Dinied 접근 권한 없음

    // 404 NOT_FOUND 잘못된 리소스 접근

    // 409 CONFLICT 중복된 리소스

    protected final int code;
    protected final String message;

    @Override
    public int getCode() { return this.code; }

    @Override
    public String getMessage() { return message; }
}
