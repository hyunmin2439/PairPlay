package com.ssafy.api.response;

import com.ssafy.domain.entity.ActivityLike;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@ApiModel("Profile Activity Like Response")
public class ProfileActivityLikeRes extends BaseResponseBody{
    long totalPages;
    long totalElements;
    List<ProfileActivityLike> list;

    public static ProfileActivityLikeRes of (Integer statusCode, String message, long totalPages, long totalElements, Page<ActivityLike> page) {
        ProfileActivityLikeRes res = new ProfileActivityLikeRes();

        res.setCode(statusCode);
        res.setMessage(message);
        res.setTotalPages(totalPages);
        res.setTotalElements(totalElements);
        
        List<ProfileActivityLike> list = new ArrayList<>();

        for (ActivityLike activityLike : page) {
            list.add(ProfileActivityLike.of(activityLike));
        }

        res.setList(list);

        return res;
    }
}
