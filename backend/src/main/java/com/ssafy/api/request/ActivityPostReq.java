package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ApiModel("MatePostReq")
public class ActivityPostReq {

    @ApiModelProperty(name = "시도")
    String sido;


    @ApiModelProperty(name = "구군")
    String gugun;

    
    @ApiModelProperty(name = "카테고리ID")
    Long categoryId;

    @ApiModelProperty(name = "모임 날짜")
    LocalDateTime meetDt;

    @ApiModelProperty(name = "공고 종료일")
    LocalDateTime closeDt;
    
    @ApiModelProperty(name = "제목")
    String title;

    @ApiModelProperty(name = "내용")
    String description;

    @ApiModelProperty(name = "연령대")
    int age;

    @ApiModelProperty(name = "성별")
    int gender;

}
