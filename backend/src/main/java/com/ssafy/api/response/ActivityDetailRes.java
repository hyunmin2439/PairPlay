package com.ssafy.api.response;

import com.ssafy.api.service.S3FileUploadService;
import com.ssafy.domain.entity.Activity;
import com.ssafy.domain.entity.Mate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @ApiModelProperty(name = "프로필 이미지")
    String profileImage;

    @ApiModelProperty(name = "닉네임")
    String Nickname;

    @ApiModelProperty(name = "성별")
    int gender;

    @ApiModelProperty(name = "연령대")
    int age;

    @ApiModelProperty(name= "찜하기")
    boolean isLike;


    public static ActivityDetailRes of(Mate mate, Long memberId, String profileImage ) {


        ActivityDetailRes res = new ActivityDetailRes();

        res.setActivityId(mate.getActivityId().getId());
        res.setCreatedDate(mate.getActivityId().getCreatedDate());
        res.setCategoryId(mate.getActivityId().getCategoryId());
        res.setTitle(mate.getActivityId().getTitle());
        res.setDescription(mate.getActivityId().getDescription());
        res.setLocation(mate.getActivityId().getLocation());
        res.setGender(mate.getActivityId().getGender());
        res.setAge(mate.getActivityId().getAge());
        res.setProfileImage(profileImage);
        res.setNickname(mate.getMemberId().getNickname());
        mate.getActivityId().getActivityLikeList().forEach( like -> {
            if(like.getMemberId().getId().equals(memberId)){
                res.setLike(true);
            }
        });

        return res;
    }
}
