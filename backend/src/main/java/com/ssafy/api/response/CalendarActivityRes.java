package com.ssafy.api.response;

import com.ssafy.domain.entity.Activity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ApiModel("Calendar Activity Resposne")
public class CalendarActivityRes extends BaseResponseBody{

    @ApiModelProperty(name = "Activity List")
    List<ActivityRes> list;

    public static CalendarActivityRes of (Integer statusCode, String message, List<Activity> activityList) {
        CalendarActivityRes res = new CalendarActivityRes();

        res.setCode(statusCode);
        res.setMessage(message);

        List<ActivityRes> list = new ArrayList<>();
        activityList.forEach(activity -> {
            list.add(ActivityRes.of(activity));
        });
        res.setList(list);

        return res;
    }

}
