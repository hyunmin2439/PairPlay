package com.ssafy.api.response;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@ApiModel("Profile Place Response")
public class ProfileMateRes extends BaseResponseBody{
    long totalPages;
    long totalElements;
    List<ProfileMate> list;

    public static ProfileMateRes of (long totalPages, long totalElements, List<ProfileMate> list) {
        ProfileMateRes res = new ProfileMateRes();

        res.setTotalPages(totalPages);
        res.setTotalElements(totalElements);

        res.setList(list);

        return res;
    }
}
