package com.ssafy.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@ApiModel("Calendar Date Response")
public class CalendarDateRes {
    @ApiModelProperty(name = "Calendar Date")
    String date;
    @ApiModelProperty(name = "Activity Count")
    int count;

    public static CalendarDateRes of (String date, int count) {
        CalendarDateRes res = new CalendarDateRes();

        res.setDate(date);
        res.setCount(count);

        return res;
    }
}
