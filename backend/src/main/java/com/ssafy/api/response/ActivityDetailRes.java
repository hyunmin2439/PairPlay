package com.ssafy.api.response;

import com.ssafy.api.service.S3FileUploadService;
import com.ssafy.domain.entity.Activity;
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

    @ApiModelProperty(name = "이미지 목록")
    List<String> multipartFile;

    @ApiModelProperty(name = "성별")
    int gender;

    @ApiModelProperty(name = "연령대")
    int age;


    private static S3FileUploadService s3FileUploadService;


    public static ActivityDetailRes of(Activity activity, Integer statusCode, String message) {




        List<String> imageList = new ArrayList<>();

        assert activity != null;
        if(activity.getMateImage() != null && activity.getMateImage().length() != 0){
            System.out.println("이미지리스트확인"+ activity.getMateImage());
            String image = activity.getMateImage();
            String[] arr = image.split(" ");

            for (String imgUrl : arr){
                imageList.add(s3FileUploadService.findImg(imgUrl));
            }
        }

        ActivityDetailRes res = new ActivityDetailRes();

        res.setActivityId(activity.getId());
        res.setCreatedDate(activity.getCreatedDate());
        res.setCategoryId(activity.getCategoryId());
        res.setTitle(activity.getTitle());
        res.setDescription(activity.getDescription());
        res.setLocation(activity.getLocation());
        res.setMultipartFile(imageList);
        res.setGender(activity.getGender());
        res.setAge(activity.getAge());
        res.setCode(statusCode);
        res.setMessage(message);
        return res;
    }
}
