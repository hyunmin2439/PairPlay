package com.ssafy.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.api.request.PlaceSearchPostReq;
import com.ssafy.domain.document.Place;
//import com.ssafy.domain.entity.QPlaceMongo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlaceRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;
    private final MongoTemplate mongoTemplate;

//    QPlaceMongo qPlaceMongo = QPlaceMongo.placeMongo;

    public PlaceRepositorySupport(JPAQueryFactory jpaQueryFactory, MongoTemplate mongoTemplate) {
        this.jpaQueryFactory = jpaQueryFactory;
        this.mongoTemplate = mongoTemplate;
    }

    public Page<Place> searchPlace(Pageable pageable, PlaceSearchPostReq searchInfo) {
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
                        )
        ).limit(pageable.getPageSize()).skip(pageable.getOffset());

        List<Place> placeList = mongoTemplate.find(query, Place.class);

        // PageableExecutionUtils 정확한 내용은 조금 더 조사해서 정리해놓을 것
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