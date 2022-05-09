package com.ssafy.domain.repository;

import com.ssafy.domain.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
        Optional<Activity> findById(Long id);
        List<Activity> findByCreateIdAndMeetDtBetween(Long id, LocalDateTime start, LocalDateTime end);
}
