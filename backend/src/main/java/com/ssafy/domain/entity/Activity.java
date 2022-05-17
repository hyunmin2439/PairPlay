package com.ssafy.domain.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Activity extends BaseEntity{

    private Long categoryId;
    private Long createId;
    private String title;
    private String description;
    private LocalDateTime meetDt;
    private LocalDateTime closeDt;
    private String location;
    private String mateImage;

    @Column(name = "is_end", columnDefinition = "BOOLEAN")
    private Boolean isEnd;

    @JsonManagedReference
    @OneToMany(mappedBy = "activityId") //참조를 당하는 쪽에서 읽기만 가능!
    @Builder.Default
    private List<Mate> mateList = new LinkedList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "activityId") //참조를 당하는 쪽에서 읽기만 가능!
    @Builder.Default
    private List<ActivityLike> activityLikeList = new LinkedList<>();

}
