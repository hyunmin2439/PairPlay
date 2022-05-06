package com.ssafy.domain.repository;

import com.ssafy.api.response.CalendarDateRes;
import com.ssafy.domain.entity.Mate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MateRepository extends JpaRepository<Mate, Long> {
    List<Mate> findByActivityId(Long activityId);

    @Query(value =  "select meet_dt as date, count(*) as count " +
                    "from mate " +
                    "join activity " +
                    "on mate.member_id_id = activity.create_id " +
                    "where mate.member_id_id = ? " +
                    "and meet_dt between ? and ? " +
                    "group by meet_dt order by date" , nativeQuery = true)
    List<CalendarDateRes> findByMemberIdAndMeetDtBefore(Long memberId, LocalDate now, LocalDate range);
}
