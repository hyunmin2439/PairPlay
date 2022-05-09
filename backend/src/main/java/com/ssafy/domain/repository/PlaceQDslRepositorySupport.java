package com.ssafy.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.api.request.PlaceSearchPostReq;
import com.ssafy.domain.document.Place;
import com.ssafy.domain.document.QPlace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class PlaceQDslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;
    private final QPlace qPlace;

    public PlaceQDslRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
        this.qPlace = QPlace.place;
    }

    public Page<Place> searchPlace(Pageable pageable, PlaceSearchPostReq searchInfo) {

//        jpaQueryFactory.select(qPlaceMongo)
//                .from(qPlaceMongo)
//                .where(
//
//                )

        return null;
    }
}
