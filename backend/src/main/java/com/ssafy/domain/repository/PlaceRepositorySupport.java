package com.ssafy.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.api.request.PlaceSearchPostReq;
import com.ssafy.domain.document.Place;
import com.ssafy.domain.document.PlaceDetail;
import com.ssafy.domain.document.QPlace;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class PlaceRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;
    private final MongoTemplate mongoTemplate;

    QPlace qPlace = QPlace.place;

    public PlaceRepositorySupport(JPAQueryFactory jpaQueryFactory, MongoTemplate mongoTemplate) {
        this.jpaQueryFactory = jpaQueryFactory;
        this.mongoTemplate = mongoTemplate;
    }

    public List<Place> getRecentViewPlaces() {

        return null;
    }

    public List<Place> getPopularPlaces() {

        return null;
    }

    public PlaceDetail detailPlace(Long placeId) {
        /**
         * lookup - 일반 RDB에서 사용하는 join의 역할
         *
         * lookup은 mongoDB에서 지양하라는 방식이며 embedded 방식을 권장하고 있다.
         * 또한 embedded방식을 사용하면 복잡한 문제를 단순히 해결 가능하다.
         * $lookup은 성능상에도 문제가 있고 noSQL 특성상 어울리지 않는 방식이다.
         * 그럼에도 lookup을 사용해서 두 Collection을 합쳐서 사용하는 이유는
         * 1. Aggregation은 lookup 뿐만 아니라 복잡한 쿼리문을 다룰 때도 사용하는 방식
         * 2. 배우면서 프로젝트를 진행하는 과정이기에 $lookup을 사용해보고자 한다.
         */


        /** 컬렉션을 JOIN하는 LookupOperation 3가지 방법 */
//        LookupOperation lookupOperation = Aggregation.lookup("review", "id", "place_id", "review");

//        LookupOperation lookupOperation = LookupOperation.newLookup().from("review").localField("id").foreignField("place_id").as("review");

        /**
         * from : 조인시킬 컬렉션 명
         * let : 파이프라인에서 사용할 필드명: 변수명
         * pipeline : 실제 조건
         * $$(달러 2개)는 조인 대상 컬렉션의 필드
         * $(달러 1개)는 현재 조회 대상 컬렉션의 필드
         * 뽑아낸 값을 as를 통해 담기
         *
         * '$lookup': {
         *  'from': 'review',
         *  'let' : { 'id' : 'id' },
         *  'pipeline': [
         *      {'$match': {'$expr':[ {'$and':[
         *          {"$place_id": "$$id"},
         *          // 추가 조건도 가능
         *      ]} ]} }
         *  ],
         *  'as' : 'reviewList'
         * }
         */
        AggregationOperation lookupOperation = context -> new Document(
                "$lookup",
                new Document("from", "review") // Document가 {}와 같은 역할을 하는 것.
                        .append("let", new Document("id",  "$id"))
                        .append("pipeline",
                                Collections.singletonList(
                                        new Document("$match", new Document("$expr", new Document("$and",
                                                Arrays.asList(
                                                        new Document("$eq", Arrays.asList("$place_id", "$$id"))
                                                )))
                                        )
                                )
                        )
                        .append("as","reviewList")
        );

        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("id").is(placeId)), // 조건을 통해 먼저 lookup할 목록을 줄임
                lookupOperation // 추후 lookup을 수행하는 것이 성능이 더 뛰어남
        );

        return mongoTemplate.aggregate(aggregation, "region", PlaceDetail.class).getUniqueMappedResult();
    }

    public Page<Place> searchPlaces(Pageable pageable, PlaceSearchPostReq searchInfo) {
        Query query = new Query();

        /**
         * Criteria 쿼리는 JPQL을 자바 코드로 작성하도록 도와주는 빌더 클래스 API
         */
        query.addCriteria(
                // 주소와 같은 구 검색
                Criteria.where("address").regex(".*" + searchInfo.getSido() + " " + searchInfo.getGugun() + ".*")
                        .andOperator(
                                // 선택된 종목 전부 선택
                                Criteria.where("category").regex("(" + searchInfo.getCategoryList().get(0) + ")")
                                        .orOperator(
                                            // 검색 단어: 체육시설명, 교통, 근처 정보, 해시태그 중에 하나라도 포함되면
                                            Criteria.where("name").regex(".*" + searchInfo.getSearchWord() + ".*"),
                                            Criteria.where("transport").regex(".*" + searchInfo.getSearchWord() + ".*"),
                                            Criteria.where("near").regex(".*" + searchInfo.getSearchWord() + ".*"),
                                            Criteria.where("hashtags").regex(".*" + searchInfo.getSearchWord() + ".*")
                                        )
                        )
        ).limit(pageable.getPageSize()).skip(pageable.getOffset());

        List<Place> placeList = mongoTemplate.find(query, Place.class);

        /**
         * QueryDSL : Type-Safe한 방법
         *
         * 추후 시간이 남으면 QueryDSL 방식으로 바꿔볼 것.
         */
//        List<Place> placeList2 = jpaQueryFactory
//                .select(qPlace)
//                .from(qPlace)
//                .where(
//                        qPlace.address.like(searchInfo.getSido() + " " + searchInfo.getGugun()),
//                        Expressions.booleanTemplate(),
//                )

        /**
         * 참조 사이트 : https://ugo04.tistory.com/142
         *
         * QueryResults를 가져오는 쿼리문을 사용할 땐, 끝에 fetchResults()라는 메서드가 붙는다.
         * 이것은 컨텐츠를 가져오는 쿼리와 total을 구하는 쿼리, 두가지의 쿼리문이 동시에 보내진다.
         * 따라서 페이지 사이즈가 토탈 카운트보다 적거나, 마지막 페이지일 경우 카운트 쿼리가 필요가 없다.
         * 이런 이유에선지 현재 프로젝트의 버전 2.6.7 버전에서는 fetchResults()가 deprecated 처리가 돼있다.
         *
         * 이런 문제점을 대체하기 위해서 따로 컨텐츠만 가져오는 쿼리로 List만 받고,
         * PageableExecutionUtils를 통해서 total을 구하는 쿼리르 따로 보내는 방식.
         */
        return PageableExecutionUtils.getPage(
                placeList,
                pageable,
                () -> mongoTemplate.count(
                        Query.of(query)
                                .limit(-1)
                                .skip(-1),
                        Place.class
                )
        );
    }

}