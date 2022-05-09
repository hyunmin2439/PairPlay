package com.ssafy.domain.repository;

import com.ssafy.domain.document.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ReviewRepository extends MongoRepository<Review, String> {
    Optional<Review> findByMemberId(Long memberId);

    Optional<Review> findByPlaceId(Long placeId);
}
