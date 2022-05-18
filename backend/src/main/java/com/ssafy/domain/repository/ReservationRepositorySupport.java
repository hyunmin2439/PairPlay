package com.ssafy.domain.repository;

import com.ssafy.domain.document.MyReservation;
import org.bson.Document;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class ReservationRepositorySupport {

    private final MongoTemplate mongoTemplate;

    public ReservationRepositorySupport(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<MyReservation> getMyReservation(Long memberId, int sw) {
        LookupOperation lookupOperation = Aggregation.lookup("region", "place_id", "id", "place");
//        LookupOperation lookupOperation2 = Aggregation.lookup("review", "review_id", "_id", "review");
        /**
         * reservation에 review_id는 String이고 참조하려는 외래키 _id는
         * review의 기본키이자 ObjectId이기 때문에 Type이 맞지 않아서 조회가 안됨.
         * 따라서 review_id와 같은 외래키는 ObjectId로 Type을 유지하는 것이 좋다.
         *
         * 하지만 이미 String으로 구현된 상태에서 다른 모든 것들을 변경하기엔 부담이 큰 상항
         * 따라서 review_id(String)을 조회할 땐 Object_id로 Type을 변경해서 조회.
         * $toObjectId를 통해서 String -> ObjectId로 변경 조회
         *
         * 추후 외래키 속성은 ObjectId로 유지하는 것이 좋을 것 같다.
         *
         * org.springframework.data.mongodb.UncategorizedMongoDbException: Command failed with error 241 (ConversionFailure): ~~
         * ObjectId로 변환하려는데 발생하는 에러, 빈값을 ObjectId로 변한 하려고 하니 에러가 생기는 것이다.
         * 일단, 다른 좋은 방법은 떠오르지 않아 24자리의 111111111111111111111111와 같은 의미 없는 문자열을 넣어줌을로써 해결했다.
         */

        AggregationOperation lookupOperation2 = context -> new Document(
                "$lookup",
                new Document("from", "review")
                        .append("let", new Document("reviewId", new Document("$toObjectId", "$review_id")))
                        .append("pipeline",
                                Collections.singletonList(
                                        new Document("$match", new Document("$expr", new Document("$and",
                                                Arrays.asList(
                                                        new Document("$eq", Arrays.asList("$_id", "$$reviewId")),
                                                        new Document("$eq", Arrays.asList("$_id", "$$reviewId"))
                                                )))
                                        )
                                )
                        )
                        .append("as","review")
        );
        
        // 모든 예약 정보를 가져옴 : 0
        MatchOperation matchOperation1 = Aggregation.match(
                Criteria.where("member_id").is(memberId)
        );

        // 예약 사용 시작 시간이 현재 시각 이전 것을 가져옴 : 1 => 사용 완료
        MatchOperation matchOperation2 = Aggregation.match(
                Criteria.where("member_id").is(memberId).andOperator(
                        Criteria.where("reserve_start_dt").lt(LocalDateTime.now().plusHours(9))
                )
        );

        // 예약 사용 종료 시간이 현재 시각 이후인 것을 가져옴 : 2 => 예약 중
        MatchOperation matchOperation3 = Aggregation.match(
                Criteria.where("member_id").is(memberId).andOperator(
                        Criteria.where("reserve_start_dt").gt(LocalDateTime.now().plusHours(9))
                )
        );

        Aggregation aggregation = Aggregation.newAggregation(
                sw == 0 ? matchOperation1 : ( sw == 1 ? matchOperation2 : matchOperation3 ), // 조건을 통해 먼저 lookup할 목록을 줄임
                lookupOperation, // 추후 lookup을 수행하는 것이 성능이 더 뛰어남
                lookupOperation2,
                Aggregation.sort(Sort.Direction.DESC, "reserve_start_dt")
        );

        return mongoTemplate.aggregate(aggregation, "reservation", MyReservation.class).getMappedResults();
    }
}
