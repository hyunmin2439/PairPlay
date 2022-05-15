package com.ssafy.domain.repository;

import com.ssafy.domain.entity.ActivityLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ActivityLikeRepository extends JpaRepository<ActivityLike, Long> {

}
