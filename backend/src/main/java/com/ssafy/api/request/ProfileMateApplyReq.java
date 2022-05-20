package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@ApiModel("ProfileMateApplyRequest")
public class ProfileMateApplyReq {

    @ApiModelProperty(name="Mate ID", example="1")
    Long id;

}
