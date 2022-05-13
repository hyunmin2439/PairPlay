package com.ssafy.domain.repository;

import com.ssafy.api.request.PlaceSearchPostReq;
import com.ssafy.domain.document.Place;
import com.ssafy.domain.document.PlaceDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlaceRepositorySupport {

    private final MongoTemplate mongoTemplate;

    public PlaceRepositorySupport(MongoTemplate mongoTemplate) { this.mongoTemplate = mongoTemplate; }

    public List<Place> getPopularPlaces(String address) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("address").regex(".*" + address + ".*")),
                Aggregation.sort(Sort.Direction.DESC, "view_cnt").and(Sort.Direction.DESC, "review_cnt").and(Sort.Direction.DESC, "like_cnt"),
                Aggregation.limit(10)
        );

        return mongoTemplate.aggregate(aggregation, "region", Place.class).getMappedResults();
    }

    public PlaceDetail detailPlace(Long placeId) {
        /** 상세한 내용 정리는 PlaceTests 쪽 참조 */
        LookupOperation lookupOperation = Aggregation.lookup("review", "id", "place_id", "reviewList");

        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("id").is(placeId)), // 조건을 통해 먼저 lookup할 목록을 줄임
                lookupOperation // 추후 lookup을 수행하는 것이 성능이 더 뛰어남
        );

        return mongoTemplate.aggregate(aggregation, "region", PlaceDetail.class).getUniqueMappedResult();
    }

    public Page<Place> searchPlaces(Pageable pageable, PlaceSearchPostReq searchInfo) {
        Query query = new Query();
        /** Criteria 쿼리는 JPQL을 자바 코드로 작성하도록 도와주는 빌더 클래스 API */
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