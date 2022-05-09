package com.ssafy.api.response;

import com.ssafy.domain.document.Place;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 요청에 따른 statusCode와 massage, 체육 시설 리스트를 내려줌
 */
@Getter
@Setter
@ApiModel("PlaceListResponse")
public class PlaceListRes extends BaseResponseBody{
    long totalPages; // 전체 페이지 수
    long totalElements; // 전체 개수
    List<PlaceRes> placeList;

    public static PlaceListRes of(Integer statusCode, String message,
                                  long totalPages, long totalElements, List<Place> list) {
        PlaceListRes res = new PlaceListRes();
        res.setCode(statusCode);
        res.setMessage(message);
        res.totalPages = totalPages;
        res.totalElements = totalElements;

        res.placeList = new ArrayList<>();
        list.forEach( place -> res.placeList.add( PlaceRes.of(place)) );

        return res;
    }
}
