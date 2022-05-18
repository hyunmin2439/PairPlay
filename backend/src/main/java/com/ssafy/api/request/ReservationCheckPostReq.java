package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@ApiModel("ReservationCheckPostRequest")
public class ReservationCheckPostReq {
    @ApiModelProperty(name="예약할 시설 Id", example="499")
    Long placeId;
    @ApiModelProperty(name="예약 날짜", example="2022-05-10")
    LocalDate reservationDt;
}
