package com.ssafy.api.response;

import com.ssafy.domain.document.Place;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
public class PlaceRes {
    Long id;
    String category;
    String address;
    List<String> bizhour;
    String homepage;
    List<String> menu;
    String name;
    List<String> img;
    String phone;
    double longitude;
    double latitude;
    String transport;
    String near;
    List<String> hashtags;
    boolean isLike;
    int viewCnt;
    int likeCnt;
    int reviewCnt;
    double score;

    public static PlaceRes of(Place place, boolean isLike) {
        PlaceRes res = new PlaceRes();
        res.setId(place.getPlaceId());
        res.setCategory(place.getCategory());
        res.setAddress(place.getAddress());
        res.setBizhour(place.getBizhour());
        res.setHomepage(place.getHomepage());
        res.setMenu(place.getMenu());
        res.setName(place.getName());
        res.setImg(place.getImg());
        res.setPhone(place.getPhone());
        res.setLongitude(place.getLongitude());
        res.setLatitude(place.getLatitude());
        res.setTransport(place.getTransport());
        res.setNear(place.getNear());
        res.setHashtags(place.getHashtags());
        res.setLike(isLike);
        res.setViewCnt(place.getViewCnt());
        res.setLikeCnt(place.getLikeCnt());
        res.setReviewCnt(place.getReviewCnt());
        res.setScore(place.getScore());
        return res;
    }
}
