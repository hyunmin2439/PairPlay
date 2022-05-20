package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel("ReservationDeleteRequest")
public class ReservationDeleteReq {
    @ApiModelProperty(name="취소할 예약 id", example="6278ff1cf91016611058d050")
    String reservationId;
}
