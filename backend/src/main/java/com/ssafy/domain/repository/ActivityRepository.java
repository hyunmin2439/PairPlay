package com.ssafy.domain.repository;


import com.ssafy.domain.entity.Activity;
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

        @Query(value = " select a.id, a.created_date, a.modified_date, a.category_id, a.title, a.description, a.meet_dt, a.close_dt,  a.create_id, a.is_end, a.location, a.mate_image " +
                " from activity a join mate m on m.activity_id_id = a.id " +
                " where m.member_id_id = ? and meet_dt between ? and ? and m.accept = 1 order by meet_dt asc ", nativeQuery = true)
        List<Activity> findByMateMemberIdAndMeetDtBetween(Long memberId, LocalDateTime start, LocalDateTime end);



        Activity findTop1ByCreateIdOrderByIdDesc(Long memberId);
}
