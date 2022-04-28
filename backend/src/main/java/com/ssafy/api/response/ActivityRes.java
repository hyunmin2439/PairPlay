package com.ssafy.api.response;

import com.ssafy.domain.entity.Activity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel("MateResponse")
public class ActivityRes {

    @ApiModelProperty(name = "Activity id")
    Long activityId;

    @ApiModelProperty(name = "생성 날짜")
    LocalDateTime createdDate;

    @ApiModelProperty(name = "카테고리 번호")
    Long categoryId;

    @ApiModelProperty(name = "제목")
    String title;

    @ApiModelProperty(name = "설명")
    String description;

    @ApiModelProperty(name = "위치 주소")
    String location;

    @ApiModelProperty(name = "팀 여부")
    boolean isTeam;

    public static ActivityRes of(Activity activity) {

        ActivityRes res = new ActivityRes();
        res.setActivityId(activity.getId());
        res.setCreatedDate(activity.getCreatedDate());
        res.setCategoryId(activity.getCategoryId());
        res.setTitle(activity.getTitle());
        res.setDescription(activity.getDescription());
        res.setLocation(activity.getLocation());
        res.setTeam(activity.isTeam());

        return res;
    }
}
