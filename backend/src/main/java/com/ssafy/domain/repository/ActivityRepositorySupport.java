package com.ssafy.domain.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.domain.entity.Activity;
import com.ssafy.domain.entity.QActivity;
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

    public Page<Activity> findAll(Pageable pageable){
        QueryResults<Activity> activities = jpaQueryFactory
                .select(qActivity)
                .from(qActivity)
                .orderBy(qActivity.id.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchResults();
        if(activities == null) return Page.empty();

        return new PageImpl<Activity>(activities.getResults(), pageable, activities.getTotal());

    }


}