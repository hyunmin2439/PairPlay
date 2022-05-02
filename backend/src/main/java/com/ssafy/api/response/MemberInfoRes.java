package com.ssafy.api.response;

import com.ssafy.domain.entity.Member;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * 유저 로그인 API ([POST] /api/v1/auth) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("MemberInfoResponse")
public class MemberInfoRes extends MemberLoginRes{
    @ApiModelProperty(name = "Member Id")
    Long memberId;
    @ApiModelProperty(name="User email")
    String email;
    @ApiModelProperty(name="User nickname")
    String nickname;
    @ApiModelProperty(name="User ProfileImg")
    String name;
    @ApiModelProperty(name="성별", example="0 (남자) or 1 (여자)")
    int gender;
    @ApiModelProperty(name="생년월일", example="1995-01-01")
    LocalDate birthDt;
    @ApiModelProperty(name="주소", example="부산광역시 싸피구 싸피동 100")
    String address;
    @ApiModelProperty(name="휴대폰 번호", example="010-1234-5678")
    String phone;
    @ApiModelProperty(name="User ProfileImg")
    String profileImg;
    @ApiModelProperty(name="User ProfileImg")
    String description;

    public static MemberInfoRes of(Member member, Integer statusCode, String message) {
        MemberInfoRes res = new MemberInfoRes();

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
        res.setDescription(member.getDescription());

        return res;
    }
    public static MemberInfoRes of(Member member, Integer statusCode, String message, String accessToken) {
        MemberInfoRes res = new MemberInfoRes();

        res.setCode(statusCode);
        res.setMessage(message);
        res.setAccessToken(accessToken);

        res.setMemberId(member.getId());
        res.setEmail(member.getEmail());
        res.setNickname(member.getNickname());
        res.setName(member.getName());
        res.setGender(member.getGender());
        res.setBirthDt(member.getBirthDt());
        res.setAddress(member.getAddress());
        res.setPhone(member.getPhone());
        res.setDescription(member.getDescription());

        return res;
    }
}
