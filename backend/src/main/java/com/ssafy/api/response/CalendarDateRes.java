package com.ssafy.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@ApiModel("Calendar Date Response")
public class CalendarDateRes {
    @ApiModelProperty(name = "Calendar Date")
    LocalDate date;
    @ApiModelProperty(name = "Activity Count")
    int count;

    public static CalendarDateRes of (LocalDate date, int count) {
        CalendarDateRes res = new CalendarDateRes();

        res.setDate(date);
        res.setCount(count);

        return res;
    }
}
