package com.ssafy.api.response;

import com.ssafy.domain.entity.Mate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel("Calendar Activity Detail Mate Response")
public class CalendarDetailMateRes {

    @ApiModelProperty(name = "참여한 Member Id")
    Long memberId;
    @ApiModelProperty(name = "프로필 이미지 사진")
    String profileImage;

    public static CalendarDetailMateRes of (Mate mate) {
        CalendarDetailMateRes res = new CalendarDetailMateRes();

        res.setMemberId(mate.getMemberId().getId());
        res.setProfileImage(mate.getMemberId().getProfileImage());

        return res;
    }
}
