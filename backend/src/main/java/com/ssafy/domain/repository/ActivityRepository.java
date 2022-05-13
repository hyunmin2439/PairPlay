package com.ssafy.domain.repository;

import com.querydsl.core.Tuple;
import com.ssafy.domain.document.ActivityDto;
import com.ssafy.domain.entity.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
        Optional<Activity> findById(Long id);
        List<Activity> findByCreateIdAndMeetDtBetween(Long id, LocalDateTime start, LocalDateTime end);

        @Query(value = " select a.id, a.created_date, a.modified_date, a.category_id, a.title, a.description, a.meet_dt, a.close_dt,  a.create_id, a.is_end, a.location " +
                " from activity a join mate m on m.activity_id_id = a.id " +
                " where m.member_id_id = ? and meet_dt between ? and ? and m.accept = 1 order by meet_dt asc ", nativeQuery = true)
        List<Activity> findByMateMemberIdAndMeetDtBetween(Long memberId, LocalDateTime start, LocalDateTime end);



//        @Query(value = " select a.id, a.created_date, a.category_id, a.create_id, a.description, a.meet_dt, a.title, a.location, m.profile_image, l.activity_id_id , m.nickname " +
//                "from activity a " +
//                "left join member m " +
//                "on a.create_id = m.id " +
//                "left join activity_like l " +
//                "on l.member_id_id = ? and l.activity_id_id = a.id " +
//                "where a.is_end = 0", nativeQuery = true)
//        Page<ActivityDto> activityLeftJoin(Long memberId, Pageable pageable);


//        @Query(value = " select * from mate")
        Page<Activity> findAllBy(Pageable pageable);

        Activity findTop1ByCreateIdOrderByIdDesc(Long memberId);
}
