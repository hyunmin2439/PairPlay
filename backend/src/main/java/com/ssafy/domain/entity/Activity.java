package com.ssafy.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Activity extends BaseEntity{

    Long categoryId;
    Long createId;
    String title;
    String description;
    LocalDateTime meetDt;
    LocalDateTime closeDt;
    String location;

    @Column(name = "is_end", columnDefinition = "BOOLEAN")
    Boolean isEnd;

}
