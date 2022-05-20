package com.ssafy.api.response;

import com.ssafy.domain.document.Place;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@ApiModel("Profile Place Like List Response")
public class ProfilePlaceLikeListRes extends BaseResponseBody {

    long totalPages;
    long totalElements;
    List<Place> list;

//    public static ProfilePlaceLikeListRes of (Integer statusCode, String message, List<Place> list) {
//        ProfilePlaceLikeListRes res = new ProfilePlaceLikeListRes();
//
//        res.setCode(statusCode);
//        res.setMessage(message);
//        res.setList(list);
//
//        return res;
//    }

    public static ProfilePlaceLikeListRes of (long totalPages, long totalElements, List<Place> list) {

        ProfilePlaceLikeListRes res = new ProfilePlaceLikeListRes();

        res.setTotalPages(totalPages);
        res.setTotalElements(totalElements);
        res.setList(list);

        return res;
    }
}
