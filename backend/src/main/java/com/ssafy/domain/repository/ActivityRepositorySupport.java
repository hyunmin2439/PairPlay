package com.ssafy.domain.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.domain.entity.Activity;
import com.ssafy.domain.entity.QActivity;

import com.ssafy.domain.entity.QActivityLike;
import com.ssafy.domain.entity.QMember;
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
    QActivityLike qActivityLike = QActivityLike.activityLike;
    QMember qMember = QMember.member;

    /**
     * 멤버 저장된 주소 없으면 공고 전체 검색
     */
    public Page<Activity> findAll(Pageable pageable){

        QueryResults<Activity> activities = jpaQueryFactory
                .select(qActivity)
                .from(qActivity)
                .where(qActivity.isEnd.isFalse())
                .orderBy(qActivity.id.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchResults();
        if(activities == null) return Page.empty();

        return new PageImpl<Activity>(activities.getResults(), pageable, activities.getTotal());

    }

//    public Page<Tuple> findAll(Pageable pageable){
//
//        QueryResults<Tuple> activities = jpaQueryFactory
//                .select(qActivity, qMember)
//                .from(qActivity)
//                .leftJoin(QMember.member, qMember).on(qActivity.createId.eq(qMember.id))
//                .where(qActivity.isEnd.isFalse())
//                .orderBy(qActivity.id.desc())
//                .limit(pageable.getPageSize())
//                .offset(pageable.getOffset())
//                .fetchResults();
//        if(activities == null) return Page.empty();
//
//        activities.getResults().forEach(res -> {
//            System.out.println("확인" + res.get(qActivity.id));
//            System.out.println("확인2" + res.get(qMember.profileImage));
//        });
//
//        return new PageImpl<Tuple>(activities.getResults(), pageable, activities.getTotal());
//
//    }

    /**
     * 멤버 저장된 주소 있으면 주소 기반 검색
     */
    public Page<Activity> findAllByLocation(Pageable pageable, String location){

        QueryResults<Activity> activities = jpaQueryFactory
                .select(qActivity)
                .from(qActivity)
                .where(qActivity.isEnd.isFalse().and(qActivity.location.contains(location)))
                .orderBy(qActivity.id.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchResults();
        if(activities == null) return Page.empty();

        return new PageImpl<Activity>(activities.getResults(), pageable, activities.getTotal());

    }




    /**
     * 카테고리, 지역, 검색어
     */
    public Page<Activity> findByCategorySearch(Pageable pageable, String location, Long categoryId, String search){

        QueryResults<Activity> activities = jpaQueryFactory
                .select(qActivity)
                .from(qActivity)
                .where(qActivity.location.eq(location)
                .and(qActivity.categoryId.eq(categoryId))
                .and(qActivity.title.contains(search).or(qActivity.description.contains(search)))
                )
                .orderBy(qActivity.id.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchResults();
        if(activities == null) return Page.empty();

        return new PageImpl<Activity>(activities.getResults(), pageable, activities.getTotal());
    }


    /**
     * 지역, 검색어
     */
    public Page<Activity> findByCategorySearch(Pageable pageable, String location, String search){

        QueryResults<Activity> activities = jpaQueryFactory
                .select(qActivity)
                .from(qActivity)
                .where(qActivity.location.eq(location).and(qActivity.title.contains(search).or(qActivity.description.contains(search))))
                .orderBy(qActivity.id.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchResults();
        if(activities == null) return Page.empty();

        return new PageImpl<Activity>(activities.getResults(), pageable, activities.getTotal());
    }


    /**
     * 카테고리, 지역
     */
    public Page<Activity> findByCategory(Pageable pageable, String location, Long categoryId){


        QueryResults<Activity> activities = jpaQueryFactory
                .select(qActivity)
                .from(qActivity)
                .where(qActivity.categoryId.eq(categoryId),qActivity.location.eq(location))
                .orderBy(qActivity.id.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchResults();
        if(activities == null) return Page.empty();

        return new PageImpl<Activity>(activities.getResults(), pageable, activities.getTotal());
    }


    /**
     * 지역
     */
    public Page<Activity> findByCategorySearch(Pageable pageable,String location){

        QueryResults<Activity> activities = jpaQueryFactory
                .select(qActivity)
                .from(qActivity)
                .where(qActivity.location.eq(location))
                .orderBy(qActivity.id.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchResults();
        if(activities == null) return Page.empty();

        return new PageImpl<Activity>(activities.getResults(), pageable, activities.getTotal());
    }

}
