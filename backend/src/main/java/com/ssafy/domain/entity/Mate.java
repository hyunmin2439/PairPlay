package com.ssafy.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Mate{

    @Id
    Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id_id")
    Member memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    Activity activityId;

    int accept;

    // 메이트 신청 수락
    public void acceptMate() {
        this.accept = 1;
    }

}
