package com.ssafy.domain.document;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import javax.persistence.Id;
import java.util.List;

@Document("region")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Place {
    @Id
    private String id;
    @Field(name = "id")
    private Long placeId;
    private String category;
    private String address;
    private List<String> bizhour;
    private String homepage;
    private List<String> menu;
    private String name;
    private List<String> img;
    private String phone;
    private double longitude;
    private double latitude;
    private String transport;
    private String near;
    private List<String> hashtags;
    @Field(name = "view_cnt")
    private int viewCnt;
    @Field(name = "like_cnt")
    private int likeCnt;
    @Field(name = "review_cnt")
    private int reviewCnt;
    private double score;

    public void modifyName(String name) { this.name = name; }

    public void increaseViewCnt() { this.viewCnt++; }

    public void modifyLikeCnt(long likeCnt){ this.likeCnt = (int)likeCnt; }

    public void modifyReviewScore(int reviewCnt, double score) {
        this.reviewCnt = reviewCnt;
        this.score = score;
    }
}
