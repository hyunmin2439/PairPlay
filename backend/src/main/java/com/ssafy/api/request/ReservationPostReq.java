package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@ApiModel("ReservationPostRequest")
public class ReservationPostReq {
    @ApiModelProperty(name="예약할 시설 Id", example="499")
    Long placeId;
    @ApiModelProperty(name="예약 날짜", example="2022-05-10")
    LocalDate reservationDt;
    @ApiModelProperty(name="시간 0 ~ 23", example="[13, 14, 15, 16 ,17]")
    List<Integer> time;
    @ApiModelProperty(name="예약 가격", example="20000")
    int price; // 가격
};