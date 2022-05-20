package com.ssafy.api.response;

import com.ssafy.domain.document.PlaceDetail;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceDetailRes extends BaseResponseBody {
    PlaceDetail placeDetail;

    public static PlaceDetailRes of(Integer statusCode, String message, PlaceDetail placeDetail) {
        PlaceDetailRes res = new PlaceDetailRes();
        res.setCode(statusCode);
        res.setMessage(message);
        res.setPlaceDetail(placeDetail);
        return res;
    }
}
