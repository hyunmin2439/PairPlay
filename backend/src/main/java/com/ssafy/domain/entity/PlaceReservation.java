package com.ssafy.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PlaceReservation extends BaseEntity {

    @Id
    Long id;
    Long memberId;
    Long placeId;
    LocalDate reservationDt;
    int time;
    int price;
}
