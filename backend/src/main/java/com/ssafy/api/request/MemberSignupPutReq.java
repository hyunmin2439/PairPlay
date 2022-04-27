package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@ApiModel("MemberSignupPuttRequest")
public class MemberSignupPutReq {
    @ApiModelProperty(name="이름", example="김싸피")
    String name;
    @ApiModelProperty(name="성별", example="0 (남자) or 1 (여자)")
    int gender;
    @ApiModelProperty(name="생년월일", example="1995-01-01")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") // 기본 의존성으로 추가되어 별로 지정 불필요
    LocalDate birthDt;
    @ApiModelProperty(name="주소", example="부산광역시 싸피구 싸피동 100")
    String address;
    @ApiModelProperty(name="휴대폰 번호", example="010-1234-5678")
    String phone;
}
