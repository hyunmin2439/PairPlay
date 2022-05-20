package com.ssafy.api.response;

import com.ssafy.domain.entity.Activity;
import com.ssafy.domain.entity.Mate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@ApiModel("Calendar Activity Detail Mate Response")
public class CalendarDetailActivityRes {

    @ApiModelProperty(name = "참여한 Activity Id")
    Long activityId;
    @ApiModelProperty(name = "참여한 Sports Category Id")
    Long categoryId;
    @ApiModelProperty(name = "생성된 Mate의 Creator Id")
    Long createId;
    @ApiModelProperty(name = "Activity Title")
    String title;
    @ApiModelProperty(name = "Activity Description")
    String description;
    @ApiModelProperty(name = "Activity Meet Date")
    LocalDateTime meetDt;
    @ApiModelProperty(name = "Activity Close Date")
    LocalDateTime closeDt;
    @ApiModelProperty(name = "Activity Location")
    String location;

    @ApiModelProperty(name = "Category Image")
    String categoryImage;

    @ApiModelProperty(name = "참여한 Mate List")
    List<CalendarDetailMateRes> mateResList;

    public static CalendarDetailActivityRes of (Activity activity, List<CalendarDetailMateRes> mateResList) {
        CalendarDetailActivityRes res = new CalendarDetailActivityRes();

        res.setActivityId(activity.getId());
        res.setCategoryId(activity.getCategoryId());
        res.setCreateId(activity.getCreateId());
        res.setTitle(activity.getTitle());
        res.setDescription(activity.getDescription());
        res.setMeetDt(activity.getMeetDt());
        res.setCloseDt(activity.getCloseDt());
        res.setLocation(activity.getLocation());

        res.setCategoryImage("https://pairplayteams.s3.ap-northeast-2.amazonaws.com/category" + activity.getCategoryId() + ".png");

        res.setMateResList(mateResList);

        return res;
    }
}
