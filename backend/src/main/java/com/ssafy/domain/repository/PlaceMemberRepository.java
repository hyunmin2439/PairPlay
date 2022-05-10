package com.ssafy.domain.repository;

import com.ssafy.domain.document.PlaceMember;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PlaceMemberRepository extends MongoRepository<PlaceMember, String> {
    Optional<PlaceMember> findByMemberId(Long memberId);
    Long countByLikeItems(Long likeItems);
}
