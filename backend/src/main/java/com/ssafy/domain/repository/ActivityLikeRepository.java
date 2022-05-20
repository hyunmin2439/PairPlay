package com.ssafy.domain.repository;

import com.ssafy.domain.entity.ActivityLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ActivityLikeRepository extends JpaRepository<ActivityLike, Long> {
    Page<ActivityLike> findByMemberId_Id(Long memberId, Pageable pageable);
}
