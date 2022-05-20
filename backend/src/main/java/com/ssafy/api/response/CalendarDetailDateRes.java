package com.ssafy.api.response;

import com.ssafy.domain.entity.Activity;
import com.ssafy.domain.entity.Mate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@ApiModel("Calendar Activity Detail Response")
public class CalendarDetailDateRes extends BaseResponseBody{

    @ApiModelProperty(name = "Calendar Activity List")
    List<CalendarDetailActivityRes> calendarDetailActivityResList;

    public static CalendarDetailDateRes of (Integer statusCode, String message, List<CalendarDetailActivityRes> calendarDetailActivityResList) {
        CalendarDetailDateRes res = new CalendarDetailDateRes();

        res.setCode(statusCode);
        res.setMessage(message);
        res.setCalendarDetailActivityResList(calendarDetailActivityResList);

        return res;
    }
}
