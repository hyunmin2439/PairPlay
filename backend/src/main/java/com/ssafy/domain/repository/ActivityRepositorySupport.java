package com.ssafy.domain.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.domain.entity.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class ActivityRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public ActivityRepositorySupport(JPAQueryFactory jpaQueryFactory){
        this.jpaQueryFactory = jpaQueryFactory;
    }

    QActivity qActivity = QActivity.activity;
    QMate qmate = QMate.mate;

    /**
     * 멤버 저장된 주소 없으면 공고 전체 검색
     */
    public Page<Mate> findAllByActivityId_CreateIdEqualsMemberId_Id(Pageable pageable) {

        QueryResults<Mate> activities = jpaQueryFactory
                .selectFrom(qmate)
                .join(qmate.activityId, qActivity).on(qmate.activityId.id.eq(qActivity.id))
                .where(qmate.activityId.id.eq(qActivity.id).and(qmate.memberId.id.eq(qActivity.createId)).and(qActivity.isEnd.isFalse()))
                .orderBy(qActivity.id.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchResults();

        if (activities == null) return Page.empty();


        return new PageImpl<Mate>(activities.getResults(), pageable, activities.getTotal());
    }


    /**
     * 멤버 저장된 주소 있으면 주소 기반 검색
     */
    public Page<Mate> findAllByLocation(Pageable pageable, String location){


        QueryResults<Mate> activities = jpaQueryFactory
                .selectFrom(qmate)
                .join(qmate.activityId, qActivity).on(qmate.activityId.id.eq(qActivity.id))
                .where(qmate.activityId.id.eq(qActivity.id).and(qmate.memberId.id.eq(qActivity.createId)).and(qActivity.isEnd.isFalse()).and(qActivity.location.contains(location)))
                .orderBy(qActivity.id.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchResults();

        return new PageImpl<Mate>(activities.getResults(), pageable, activities.getTotal());

    }




//    /**
//     * 카테고리, 지역, 검색어
//     */
    public Page<Mate> findByCategorySearch(Pageable pageable, String location, Long categoryId, String search){


        QueryResults<Mate> activities = jpaQueryFactory
                .selectFrom(qmate)
                .join(qmate.activityId, qActivity).on(qmate.activityId.id.eq(qActivity.id))
                .where(qmate.activityId.id.eq(qActivity.id).and(qmate.memberId.id.eq(qActivity.createId)).and(qActivity.isEnd.isFalse()).and(qActivity.location.contains(location))
                        .and(qActivity.categoryId.eq(categoryId)).and(qActivity.title.contains(search).or(qActivity.description.contains(search))))
                .orderBy(qActivity.id.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchResults();
        if(activities == null) return Page.empty();

        return new PageImpl<Mate>(activities.getResults(), pageable, activities.getTotal());
    }
//
//
//    /**
//     *  검색어
//     */
    public Page<Mate> findBySearch(Pageable pageable, String search){


        QueryResults<Mate> activities = jpaQueryFactory
                .selectFrom(qmate)
                .join(qmate.activityId, qActivity).on(qmate.activityId.id.eq(qActivity.id))
                .where(qmate.activityId.id.eq(qActivity.id).and(qmate.memberId.id.eq(qActivity.createId)).and(qActivity.isEnd.isFalse())
                        .and(qActivity.title.contains(search).or(qActivity.description.contains(search))))
                .orderBy(qActivity.id.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchResults();
        if(activities == null) return Page.empty();

        return new PageImpl<Mate>(activities.getResults(), pageable, activities.getTotal());
    }


//    /**
//     *  지역 + 검색어
//     */
    public Page<Mate> findBySearchAndLocation(Pageable pageable, String search, String location){


        QueryResults<Mate> activities = jpaQueryFactory
                .selectFrom(qmate)
                .join(qmate.activityId, qActivity).on(qmate.activityId.id.eq(qActivity.id))
                .where(qmate.activityId.id.eq(qActivity.id).and(qmate.memberId.id.eq(qActivity.createId)).and(qActivity.isEnd.isFalse()).and(qActivity.location.eq(location))
                        .and(qActivity.title.contains(search).or(qActivity.description.contains(search))))
                .orderBy(qActivity.id.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchResults();
        if(activities == null) return Page.empty();

        return new PageImpl<Mate>(activities.getResults(), pageable, activities.getTotal());
    }

    /**
     * 카테고리
     */
    public Page<Mate> findByCategory(Pageable pageable, Long categoryId){

        QueryResults<Mate> activities = jpaQueryFactory
                .selectFrom(qmate)
                .join(qmate.activityId, qActivity).on(qmate.activityId.id.eq(qActivity.id))
                .where(qmate.activityId.id.eq(qActivity.id).and(qmate.memberId.id.eq(qActivity.createId)).and(qActivity.isEnd.isFalse())
                        .and(qActivity.categoryId.eq(categoryId)))
                .orderBy(qActivity.id.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchResults();
        if(activities == null) return Page.empty();

        return new PageImpl<Mate>(activities.getResults(), pageable, activities.getTotal());
    }


    /**
     * 카테고리,지역
     */
    public Page<Mate> findByCategoryAndLocation(Pageable pageable, Long categoryId, String location){

        QueryResults<Mate> activities = jpaQueryFactory
                .selectFrom(qmate)
                .join(qmate.activityId, qActivity).on(qmate.activityId.id.eq(qActivity.id))
                .where(qmate.activityId.id.eq(qActivity.id).and(qmate.memberId.id.eq(qActivity.createId)).and(qActivity.isEnd.isFalse())
                        .and(qActivity.categoryId.eq(categoryId)).and(qActivity.location.eq(location)))
                .orderBy(qActivity.id.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchResults();
        if(activities == null) return Page.empty();

        return new PageImpl<Mate>(activities.getResults(), pageable, activities.getTotal());
    }
}
