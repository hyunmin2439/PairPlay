package com.ssafy.api.response;

import com.ssafy.domain.entity.Activity;
import com.ssafy.domain.entity.Mate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Getter
@Setter
@ApiModel("Calendar Activity Detail Response")
public class CalendarDetailRes extends BaseResponseBody{

    @ApiModelProperty(name = "Activity List")
    List<ActivityRes> activityResList;
    @ApiModelProperty(name = "Mate List")
    List<CalendarDetailMateRes> calendarMateList;

    public static CalendarDetailRes of (Integer statusCode, String message, List<Activity> activityList, List<Mate> mateList) {
        CalendarDetailRes res = new CalendarDetailRes();

        res.setCode(statusCode);
        res.setMessage(message);

        List<ActivityRes> activityResList = new ArrayList<>();
        activityList.forEach(activity -> {
            activityResList.add(ActivityRes.of(activity));
        });
        res.setActivityResList(activityResList);

        List<CalendarDetailMateRes> calendarMateList = new ArrayList<>();
        mateList.forEach(mate -> {
            calendarMateList.add(CalendarDetailMateRes.of(mate));
        });

        return res;
    }
}
