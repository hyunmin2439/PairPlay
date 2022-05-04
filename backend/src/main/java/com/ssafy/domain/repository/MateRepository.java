package com.ssafy.domain.repository;

import com.ssafy.domain.entity.Mate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MateRepository extends JpaRepository<Mate, Long> {
    List<Mate> findByActivityId(Long activityId);
}
