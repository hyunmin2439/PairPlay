package com.ssafy.domain.document;

import com.ssafy.common.util.CategoryConverter;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PlaceDetail {
    @Id
    private String id;
    @Field(name = "id")
    private Long placeId;
    private String category;
    private String categoryImage;
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
    private boolean isLike;
    @Field(name = "view_cnt")
    private int viewCnt;
    @Field(name = "like_cnt")
    private int likeCnt;
    @Field(name = "review_cnt")
    private int reviewCnt;
    private double cleanness;
    private double place;
    private double location;
    private double price;
    private double score;
    private List<Review> reviewList;

    public void setLike() {
       this.isLike = true;
    }

    public void setCategoryImage() {
        this.categoryImage = "https://pairplayteams.s3.ap-northeast-2.amazonaws.com/category"
                + CategoryConverter.convertToInt(this.category) + ".jpg";
    }

    public void setDetailScore(double[] detailScore) {
        this.cleanness = detailScore[0];
        this.place = detailScore[1];
        this.location = detailScore[2];
        this.price = detailScore[3];
    }
}
