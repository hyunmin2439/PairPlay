package com.ssafy.domain.repository;

import com.ssafy.domain.document.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends MongoRepository<Reservation, String> {
    Optional<Reservation> findByMemberId(Long memberId);

    List<Reservation> findByPlaceIdAndReserveStartDtBetween(Long placeId, LocalDateTime findStartDt, LocalDateTime findEndDt);
}
