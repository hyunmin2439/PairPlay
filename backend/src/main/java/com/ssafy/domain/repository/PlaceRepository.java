package com.ssafy.domain.repository;

import com.ssafy.domain.document.Place;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PlaceRepository extends MongoRepository<Place, String> {
    List<Place> findByCategory(String category);
}
