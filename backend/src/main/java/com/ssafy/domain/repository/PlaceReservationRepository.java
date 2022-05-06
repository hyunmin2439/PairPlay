package com.ssafy.domain.repository;

import com.ssafy.api.response.CalendarDateRes;
import com.ssafy.domain.entity.PlaceReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PlaceReservationRepository extends JpaRepository<PlaceReservation, Long> {
    @Query(value =  "select reservation_dt as date, count(*) as count " +
                    "from PlaceReservation " +
                    "where memberId = ? "+
                    "and meet_dt between ? and ? " +
                    "group by meet_dt order by date", nativeQuery = true)
    List<CalendarDateRes> findByMemberIdAndDateBetween(Long memberId, LocalDate now, LocalDate range);
}
