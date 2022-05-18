package com.ssafy.api.response;

import com.ssafy.api.service.S3FileUploadService;
import com.ssafy.domain.entity.Mate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

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

    private static S3FileUploadService s3FileUploadService;

    public ActivityRes(){}



    public static ActivityRes of(Mate mate, Long memberId, String profileImage) {


        ActivityRes res = new ActivityRes();

        res.setActivityId(mate.getActivityId().getId());
        res.setCreatedDate(mate.getActivityId().getCreatedDate());
        res.setCategoryId(mate.getActivityId().getCategoryId());
        res.setTitle(mate.getActivityId().getTitle());
        res.setDescription(mate.getActivityId().getDescription());
        res.setLocation(mate.getActivityId().getLocation());
        res.setNickname(mate.getMemberId().getNickname());

        res.setProfileImage(profileImage);

        mate.getActivityId().getActivityLikeList().forEach( like -> {
            if(like.getMemberId().getId().equals(memberId)){
                res.setLike(true);
            }
        });

        return res;
    }

}
