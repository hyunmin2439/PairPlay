package com.ssafy.domain.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.util.List;

@Document("member")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceMember {
    @Id
    private String id;
    @Field(name = "member_id")
    private Long memberId; // 유저 RDB id값
    @Field(name = "recent_items")
    private List<Long> recentItems; // 최근 본 목록
    @Field(name = "like_items")
    private List<Long> likeItems; // 좋아하는 목록
}
