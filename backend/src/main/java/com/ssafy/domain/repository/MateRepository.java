package com.ssafy.domain.repository;

import com.ssafy.api.response.CalendarDate;
import com.ssafy.api.response.CalendarDateRes;
import com.ssafy.domain.entity.Activity;
import com.ssafy.domain.entity.Mate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MateRepository extends JpaRepository<Mate, Long> {

    List<Mate> findByActivityId_Id( @Param("activity_id_id") Long activityId );

    @Query(value =  "SELECT date_format(meet_dt, '%Y-%m-%d') as date, count(*) as count " +
                    "FROM mate JOIN activity ON mate.activity_id_id = activity.id " +
                    "where mate.member_id_id = ? and meet_dt between ? and ? and mate.accept = 1 " +
                    "GROUP BY date_format(meet_dt, '%Y-%m-%d') ORDER BY date ASC" , nativeQuery = true)
    List<CalendarDate> findByMemberIdAndMeetDtBefore(Long memberId, LocalDate from, LocalDate to);

//    List<Mate> findByActivityId_CreateIdAndMemberId_IdNotAndActivityId_MeetDtAfterOrderById(Long createId, Long memberId, LocalDateTime now);
//    List<Mate> findByMemberId_IdAndActivityId_CreateIdNotAndActivityId_MeetDtAfterOrderById(Long memberId, Long createId, LocalDateTime now);

    Page<Mate> findByActivityId_CreateIdAndMemberId_IdNotAndActivityId_MeetDtAfterOrderById(Long createId, Long memberId, LocalDateTime now, Pageable pageable);
    Page<Mate> findByMemberId_IdAndActivityId_CreateIdNotAndActivityId_MeetDtAfterOrderById(Long memberId, Long createId, LocalDateTime now, Pageable pageable);


    void deleteByActivityId_Id(Long activityId);

    Mate findByActivityId_IdAndMemberId_Id(Long activityId, Long memberId);
}
