package com.ssafy.api.response;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@ApiModel("Reservation Check Res")
public class ReservationCheckRes extends BaseResponseBody {
    Map<Integer, Integer> times;

    public static ReservationCheckRes of (Integer statusCode, String message, Map<Integer, Integer> times) {
        ReservationCheckRes res = new ReservationCheckRes();
        res.setCode(statusCode);
        res.setMessage(message);
        res.setTimes(times);
        return res;
    }
}
