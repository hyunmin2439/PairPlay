package com.ssafy.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Mate{

    @Id
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    Member memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    Activity activityId;

    int accept;



}
