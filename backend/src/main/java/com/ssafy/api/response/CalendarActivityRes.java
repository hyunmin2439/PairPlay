package com.ssafy.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("Calendar Activity Response")
public class CalendarActivityRes extends BaseResponseBody{

    @ApiModelProperty(name = "Calendar Date List")
    List<CalendarDateRes> list;

    public static CalendarActivityRes of (Integer statusCode, String message, List<CalendarDateRes> dateList) {
        CalendarActivityRes res = new CalendarActivityRes();

        res.setCode(statusCode);
        res.setMessage(message);
        res.setList(dateList);

        return res;
    }

}
