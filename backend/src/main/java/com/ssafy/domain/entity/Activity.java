package com.ssafy.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDateTime;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Activity extends BaseEntity{

    boolean isTeam;
    int categoryId;
    Long crateId;
    String title;
    String description;
    LocalDateTime meetDt;
    LocalDateTime closeDt;
    String location;

}
