package com.ssafy.domain.repository;

import com.ssafy.api.response.CalendarDate;
import com.ssafy.api.response.CalendarDateRes;
import com.ssafy.domain.entity.Mate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MateRepository extends JpaRepository<Mate, Long> {

    List<Mate> findByActivityId_Id( @Param("activity_id_id") Long activityId );

    @Query(value =  "SELECT date_format(meet_dt, '%Y-%m-%d') as date, count(*) as count " +
                    "FROM mate JOIN activity ON mate.activity_id_id = activity.id " +
                    "where mate.member_id_id = ? and meet_dt between ? and ? " +
                    "GROUP BY date_format(meet_dt, '%Y-%m-%d') ORDER BY date ASC" , nativeQuery = true)
    List<CalendarDate> findByMemberIdAndMeetDtBefore(Long memberId, LocalDate from, LocalDate to);
}
