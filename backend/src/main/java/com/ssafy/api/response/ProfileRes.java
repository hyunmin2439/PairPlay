package com.ssafy.api.response;

import com.ssafy.domain.entity.Member;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@ApiModel("ProfileResponse")
public class ProfileRes extends BaseResponseBody {
    @ApiModelProperty(name = "Member Id")
    Long memberId;
    @ApiModelProperty(name = "Email")
    String email;
    @ApiModelProperty(name = "Nickname")
    String nickname;
    @ApiModelProperty(name = "Name")
    String name;
    @ApiModelProperty(name = "Gender -> 0: male / 1: female")
    int gender;
    @ApiModelProperty(name = "Date of Birth")
    LocalDate birthDt;
    @ApiModelProperty(name = "Address")
    String address;
    @ApiModelProperty(name = "Phone")
    String phone;
    @ApiModelProperty(name = "Profile Image URL")
    String profileImage;
    @ApiModelProperty(name = "ENABLE")
    boolean enable;
    @ApiModelProperty(name = "Description")
    String description;

    public static ProfileRes of(Member member, Integer statusCode, String message) {
        ProfileRes res = new ProfileRes();

        res.setCode(statusCode);
        res.setMessage(message);

        res.setMemberId(member.getId());
        res.setEmail(member.getEmail());
        res.setNickname(member.getNickname());
        res.setName(member.getName());
        res.setGender(member.getGender());
        res.setBirthDt(member.getBirthDt());
        res.setAddress(member.getAddress());
        res.setPhone(member.getPhone());
        res.setProfileImage(member.getProfileImage());
        res.setEnable(member.isEnable());
        res.setDescription(member.getDescription());

        return res;
    }
}
