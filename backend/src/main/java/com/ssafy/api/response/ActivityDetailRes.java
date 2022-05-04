package com.ssafy.api.response;

import com.ssafy.domain.entity.Activity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel("Mate Detail Response")
public class ActivityDetailRes extends BaseResponseBody{

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

    public static ActivityDetailRes of(Activity activity, Integer statusCode, String message) {

        ActivityDetailRes res = new ActivityDetailRes();

        res.setActivityId(activity.getId());
        res.setCreatedDate(activity.getCreatedDate());
        res.setCategoryId(activity.getCategoryId());
        res.setTitle(activity.getTitle());
        res.setDescription(activity.getDescription());
        res.setLocation(activity.getLocation());

        res.setCode(statusCode);
        res.setMessage(message);
        return res;
    }
}
