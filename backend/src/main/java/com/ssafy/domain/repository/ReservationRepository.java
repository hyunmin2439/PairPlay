package com.ssafy.domain.repository;

import com.ssafy.domain.document.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ReservationRepository extends MongoRepository<Reservation, String> {
    Optional<Reservation> findByMemberId(Long memberId);

    Optional<Reservation> findByPlaceId(Long placeId);
}
