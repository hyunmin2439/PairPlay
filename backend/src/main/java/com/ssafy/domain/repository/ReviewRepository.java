package com.ssafy.domain.repository;

import com.ssafy.domain.document.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends MongoRepository<Review, String> {
    List<Review> findByMemberId(Long memberId);

    List<Review> findByPlaceId(Long placeId);

    Optional<Review> findByReservationId(String reservationId);
}
