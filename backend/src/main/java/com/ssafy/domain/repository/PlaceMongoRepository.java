package com.ssafy.domain.repository;

import com.ssafy.domain.entity.PlaceMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PlaceMongoRepository extends MongoRepository<PlaceMongo, String> {
    List<PlaceMongo> findByCategory(String category);
}
