package com.ssafy.domain.document;

import com.ssafy.domain.entity.Activity;
import com.ssafy.domain.entity.ActivityLike;
import com.ssafy.domain.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

public interface ActivityDto {

//    Member getMember();
//    Activity getActivity();
//    ActivityLike getActivityLike();
    
     Long getId();
     LocalDateTime getCreatedDate();
     Long getCategoryId();
     Long getCreateId();
     String getDescription();
     LocalDateTime getMeetDt();
     String getTitle();
     String getLocation();
     String getProfileImage();
     Long getActivityId();
     String getNickname();
}
