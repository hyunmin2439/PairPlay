package com.ssafy.api.response;

import com.ssafy.domain.entity.Mate;
import com.ssafy.domain.entity.Member;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@ApiModel("Profile Mate")
public class ProfileMate {

    // Activity 정보
    Long categoryId;
    Long createId;
    String title;
    String description;
    LocalDateTime meetDt;
    LocalDateTime closeDt;
    String location;

    @Column(name = "is_end", columnDefinition = "BOOLEAN")
    Boolean isEnd;

    // Mate ID
    Long id;
    // 해당 Mate가 수락되었는지 보류중인지 판단할 수 있는 Accept 필요
    int accept;
    
    // Activity 공고 주인 정보
    Long memberId;
    String email;
    String nickname;
    String name;
    String profileImage; // 프로필 이미지 주소


    public static ProfileMate of (Mate mate) {
        ProfileMate dto = new ProfileMate();

        dto.setCategoryId(mate.getActivityId().getCategoryId());
        dto.setCreateId(mate.getActivityId().getCreateId());
        dto.setTitle(mate.getActivityId().getTitle());
        dto.setDescription(mate.getActivityId().getDescription());
        dto.setMeetDt(mate.getActivityId().getMeetDt());
        dto.setCloseDt(mate.getActivityId().getCloseDt());

        dto.setIsEnd(mate.getActivityId().getIsEnd());

        dto.setId(mate.getId());
        dto.setAccept(mate.getAccept());

        dto.setMemberId(mate.getMemberId().getId());
        dto.setEmail(mate.getMemberId().getEmail());
        dto.setNickname(mate.getMemberId().getNickname());
        dto.setName(mate.getMemberId().getName());
        dto.setProfileImage(mate.getMemberId().getProfileImage());

        return dto;
    }

    public static ProfileMate of (Mate mate, Member member) {
        ProfileMate dto = new ProfileMate();

        dto.setCategoryId(mate.getActivityId().getCategoryId());
        dto.setCreateId(mate.getActivityId().getCreateId());
        dto.setTitle(mate.getActivityId().getTitle());
        dto.setDescription(mate.getActivityId().getDescription());
        dto.setMeetDt(mate.getActivityId().getMeetDt());
        dto.setCloseDt(mate.getActivityId().getCloseDt());

        dto.setIsEnd(mate.getActivityId().getIsEnd());

        dto.setId(mate.getId());
        dto.setAccept(mate.getAccept());

        dto.setMemberId(member.getId());
        dto.setEmail(member.getEmail());
        dto.setNickname(member.getNickname());
        dto.setName(member.getName());
        dto.setProfileImage(member.getProfileImage());

        return dto;
    }
}
