package com.ssafy.common.statuscode;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ActivityCode implements StatusCode{

    //200 Success 요청 성공
    SUCCESS_MATE_LIST(200, "메이트 리스트 조회 성공했습니다."),
    SUCCESS_GET_DETAIL(200, "메이트 공고 상세 조회 성공했습니다."),
    SUCCESS_ACTIVITY_CREATE(200, "메이트 공고 등록 성공했습니다."),
    SUCCESS_ACTIVITY_REGISTER(200, "메이트 신청 성공했습니다."),
    SUCCESS_ACTIVITY_LIKE(200,"메이트 찜하기 성공했습니다."),
    SUCCESS_DELETE_ACTIVITY(200, "메이트공고 삭제를 성공했습니다"),

    FAIL_MATE_IMAGE_S3_UPLOAD_ERROR(404, "공고 이미지를 S3서버에 업로드하지 못했습니다."),
    FAIL_MEMBER_NOT_FOUND(404, "해당 유저 정보를 찾지 못했습니다."),
    FAIL_ACTIVITY_NOT_FOUND(404, "해당 공고를 찾지 못했습니다.")

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
