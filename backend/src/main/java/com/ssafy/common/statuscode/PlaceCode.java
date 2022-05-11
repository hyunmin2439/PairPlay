package com.ssafy.common.statuscode;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PlaceCode implements StatusCode {
    // 200 Success 요청 성공
    SUCCESS_DETAIL_PLACE(200, "체육 시설 상세 조회에 성공했습니다."),
    SUCCESS_SEARCH_PLACE(200, "체육 시설 목록 검색에 성공했습니다."),
    SUCCESS_UPDATE_LIKE_PLACE(200, "체육 시설 찜 등록/삭제에 성공했습니다."),
    SUCCESS_POST_PLACE_REVIEW(200, "체육 시설 리뷰 등록에 성공했습니다."),
    SUCCESS_MODIFY_PLACE_REVIEW(200, "체육 시설 리뷰 수정에 성공했습니다."),
    SUCCESS_DELETE_PLACE_REVIEW(200, "체육 시설 리뷰 삭제에 성공했습니다."),
    SUCCESS_RESERVE_PLACE(200, "체육 시설 예약 등록에 성공했습니다."),
    SUCCESS_CANCEL_PLACE(200, "체육 시설 예약 취소에 성공했습니다."),

    //400 BAD_REQUEST 잘못된 요청
    FAIL_NOT_YET_POST_REVIEW(400, "체육 시설 이용 다음 날부터 리뷰를 작성할 수 있습니다."),
    FAIL_RESERVE_BEFORE_NOW_DATE(400, "현재 날짜 보다 이전에는 예약을 등록할 수 없습니다."),
    FAIL_CANCEL_AFTER_RESERVATION_DATE(400, "체육 시설 이용 날짜 이후에는 예약을 취소할 수 없습니다."),

    // 401 Access Dinied 접근 권한 없음
    FAIL_NOT_EQUAL_MEMBER(401, "예약한 유저와 로그인 된 유저 정보가 일치하지 않습니다."),

    // 404 NOT_FOUND 잘못된 리소스 접근
    FAIL_PLACE_NOT_FOUND(404, "체육시설의 정보를 찾을 수 없습니다."),
    FAIL_RESERVATION_NOT_FOUND(404, "예약 정보를 찾을 수 없습니다."),
    FAIL_REVIEW_NOT_FOUND(404, "리뷰 정보를 찾을 수 없습니다.");

    // 409 CONFLICT 중복된 리소스

    protected final int code;
    protected final String message;

    @Override
    public int getCode() { return this.code; }

    @Override
    public String getMessage() { return message; }
}
