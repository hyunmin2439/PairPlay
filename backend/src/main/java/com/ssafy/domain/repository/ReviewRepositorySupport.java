package com.ssafy.domain.repository;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepositorySupport {

    private final MongoTemplate mongoTemplate;

    public ReviewRepositorySupport(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

//    public List<MyReview> getMyReviewList(Long memberId) {
//        LookupOperation lookupOperation = Aggregation.lookup("region", "place_id", "id", "placeInfo");
//        LookupOperation lookupOperation2 = Aggregation.lookup("reservation", "_id", "review_id", "reservationInfo");
//
//        AggregationOperation lookupOperation = context -> new Document(
//                "$lookup",
//                new Document("from", "region")
//                        .append("let", new Document("place_id",  "$place_id"))
//                        .append("pipeline",
//                                Collections.singletonList(
//                                        new Document("$match", new Document("$expr", new Document("$and",
//                                                Arrays.asList(
//                                                        new Document("$eq", Arrays.asList("$id", "$$place_id"))
//                                                )))
//                                        )
//                                )
//                        )
//                        .append("as","placeInfo")
//        );
//
//        Aggregation aggregation = Aggregation.newAggregation(
//                Aggregation.match(Criteria.where("member_id").is(memberId)), // 조건을 통해 먼저 lookup할 목록을 줄임
//                lookupOperation, // 추후 lookup을 수행하는 것이 성능이 더 뛰어남
//                lookupOperation2
//        );
//
//        return mongoTemplate.aggregate(aggregation, "review", MyReview.class).getMappedResults();
//    }
}
