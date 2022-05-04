package com.ssafy.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("Profile Place Response")
public class ProfilePlaceRes extends BaseResponseBody{

    @ApiModelProperty(name = "Place Reservation List")
    List<ProfileReservationRes> reservationList;

    public static ProfilePlaceRes of (Integer statusCode, String message, List<ProfileReservationRes> reservationList) {
        ProfilePlaceRes res = new ProfilePlaceRes();

        res.setCode(statusCode);
        res.setMessage(message);
        res.setReservationList(reservationList);

        return res;
    }

}
