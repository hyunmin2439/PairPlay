package com.ssafy.api.response;


import com.ssafy.domain.entity.Activity;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ApiModel("MateListResponse")
public class ActivityListRes {

    public static Page<ActivityRes> of(Page<Activity> activityList){
        List<ActivityRes> list = new ArrayList<>();

        Pageable pageable = activityList.getPageable();
        long total = activityList.getTotalElements();

        activityList.forEach(activity -> {
            list.add(ActivityRes.of(activity));
        });

        return new PageImpl<ActivityRes>(list, pageable, total);
    }





}
