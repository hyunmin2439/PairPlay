package com.ssafy.api.response;


import com.querydsl.core.Tuple;
import com.ssafy.domain.document.ActivityDto;
import com.ssafy.domain.entity.Activity;
import com.ssafy.domain.entity.Mate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ApiModel("activity List Response")
public class ActivityListRes extends BaseResponseBody{

    @ApiModelProperty(name = "activity List")
    Page<ActivityRes> list;


    public static ActivityListRes of(Page<Mate> activityList, Integer statusCode, String message){



        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());

        ActivityListRes activityListRes = new ActivityListRes();
        activityListRes.setCode(statusCode);
        activityListRes.setMessage(message);

        List<ActivityRes> list = new ArrayList<>();

        Pageable pageable = activityList.getPageable();
        long total = activityList.getTotalElements();

        activityList.forEach(activity -> {
            list.add(ActivityRes.of(activity, memberId));
        });


        activityListRes.setList(new PageImpl<ActivityRes>(list, pageable, total));

        return activityListRes;
    }

//    public static ActivityListRes of(Page<ActivityDto> activityList, Integer statusCode, String message){
//
//        ActivityListRes activityListRes = new ActivityListRes();
//        activityListRes.setCode(statusCode);
//        activityListRes.setMessage(message);
//
//        List<ActivityRes> list = new ArrayList<>();
//
//        Pageable pageable = activityList.getPageable();
//        long total = activityList.getTotalElements();
//
//        activityList.forEach(activity -> {
//            list.add(ActivityRes.of(activity));
//        });
//
//        activityListRes.setList(new PageImpl<ActivityRes>(list, pageable, total));
//
//        return activityListRes;
//    }




}
