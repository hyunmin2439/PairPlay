package com.ssafy.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.api.request.PlaceSearchPostReq;
import com.ssafy.domain.document.Place;
import com.ssafy.domain.document.QPlace;
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

    QPlace qPlace = QPlace.place;

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