package com.ssafy.domain.repository;

import com.ssafy.domain.document.Place;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends MongoRepository<Place, String> {
    Optional<Place> findByPlaceId(Long placeId);
    List<Place> findByPlaceIdIn(List<Long> placeId);
}
