package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ApiModel("MatePostReq")
public class ActivityPostReq {

    @ApiModelProperty(name = "주소")
    String location;


    @ApiModelProperty(name = "카테고리ID")
    Long categoryId;

    @ApiModelProperty(name = "모임 날짜")
    LocalDateTime meetDt;

    @ApiModelProperty(name = "제목")
    String title;

    @ApiModelProperty(name = "내용")
    String description;

    @ApiModelProperty(name = "이미지 리스트")
    List<MultipartFile> multipartFile;

}
