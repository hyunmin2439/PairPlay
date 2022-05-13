package com.ssafy.api.response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("Reservation List Res")
public class ReservationListRes extends BaseResponseBody{

    long totalPages;
    long totalElements;
    List<ReservationRes> list;

//    public static ReservationListRes of (Integer statusCode, String message,
//                                         List<ReservationRes> list) {
//        ReservationListRes res = new ReservationListRes();
//
//        res.setCode(statusCode);
//        res.setMessage(message);
//        res.setList(list);
//
//        return res;
//    }

    public static ReservationListRes of (long totalPages, long totalElements, List<ReservationRes> list) {

        ReservationListRes res = new ReservationListRes();

        res.setTotalPages(totalPages);
        res.setTotalElements(totalElements);
        res.setList(list);

        return res;
    }
}
