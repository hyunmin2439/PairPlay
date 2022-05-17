package com.ssafy.api.response;

import com.ssafy.domain.entity.ActivityLike;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@ApiModel("Profile Activity Like")
public class ProfileActivityLike {
    // Activity 정보
    Long id;
    Long categoryId;
    Long createId;
    String title;
    String description;
    LocalDateTime meetDt;
    LocalDateTime closeDt;
    String location;

    Boolean isEnd;

    public static ProfileActivityLike of (ActivityLike activityLike) {
        ProfileActivityLike dto = new ProfileActivityLike();

        dto.setId(activityLike.getActivityId().getId());
        dto.setCategoryId(activityLike.getActivityId().getCategoryId());
        dto.setCreateId(activityLike.getActivityId().getCreateId());
        dto.setTitle(activityLike.getActivityId().getTitle());
        dto.setDescription(activityLike.getActivityId().getDescription());
        dto.setMeetDt(activityLike.getActivityId().getMeetDt());
        dto.setCloseDt(activityLike.getActivityId().getCloseDt());
        dto.setLocation(activityLike.getActivityId().getLocation());

        dto.setIsEnd(activityLike.getActivityId().getIsEnd());

        return dto;
    }
}
