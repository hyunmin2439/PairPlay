package com.ssafy.api.response;

import com.ssafy.domain.document.MyReservation;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel("Reservation Response")
public class ReservationRes {
    MyReservation myReservation;
    boolean isUsed;

    public static ReservationRes of (MyReservation myReservation, boolean isUsed) {
        ReservationRes res = new ReservationRes();

        res.setMyReservation(myReservation);
        res.setUsed(isUsed);

        return res;
    }
}
