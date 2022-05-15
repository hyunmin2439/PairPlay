package com.ssafy.api.response;

import com.querydsl.core.Tuple;
import com.ssafy.domain.document.ActivityDto;
import com.ssafy.domain.entity.Activity;
import com.ssafy.domain.entity.Mate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel("MateResponse")
public class ActivityRes{

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

    @ApiModelProperty(name = "찜하기")
    boolean like;

    @ApiModelProperty(name = "닉네임")
    String nickname;

    @ApiModelProperty(name = "이미지")
    String profileImage;

    public static ActivityRes of(Mate mate, Long memberId) {


        ActivityRes res = new ActivityRes();

        res.setActivityId(mate.getActivityId().getId());
        res.setCreatedDate(mate.getActivityId().getCreatedDate());
        res.setCategoryId(mate.getActivityId().getCategoryId());
        res.setTitle(mate.getActivityId().getTitle());
        res.setDescription(mate.getActivityId().getDescription());
        res.setLocation(mate.getActivityId().getLocation());
        res.setNickname(mate.getMemberId().getNickname());
        res.setProfileImage(mate.getMemberId().getProfileImage());

        mate.getActivityId().getActivityLikeList().forEach( like -> {
            if(like.getMemberId().getId().equals(memberId)){
                res.setLike(true);
            }
        });

        return res;
    }

//    public static ActivityRes of(ActivityDto activity) {
//        System.out.println(activity);
//
//        ActivityRes res = new ActivityRes();
////        res.setActivityId(activity.getId());
////        res.setCreatedDate(activity.getCreatedDate());
////        res.setCategoryId(activity.getCategoryId());
////        res.setTitle(activity.getTitle());
////        res.setDescription(activity.getDescription());
////        res.setLocation(activity.getLocation());
////        res.setNickname(activity.getNickname());
////        res.setProfileImage(activity.getProfileImage());
////        System.out.println(activity.getActivityIdId());
//        return res;
//    }
}
