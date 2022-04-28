package com.ssafy.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.domain.entity.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class ActivityRepositorySupport {

    JPAQueryFactory jpaQueryFactory;

    //QActivity qActivity = QAvtivity.activity;

    public Page<Activity> findAll(Pageable pageable){
//        QueryResults<Activity> activities = jpaQueryFactory
//                .select(qActivity)
//                .from(qActivity)
//                .orderBy(qActivity.id.desc)
//                .limit(pageable.getPageSize())
//                .offset(pageable.getOffset())
//                .fetchResults();
//        if(activities == null) return Page.empty();
//
//        return new PageImpl<Activity>(activities.getResults(), pageable, activities.getTotal());
        return null;
    }


}
