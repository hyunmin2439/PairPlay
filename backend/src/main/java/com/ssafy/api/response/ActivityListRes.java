package com.ssafy.api.response;


import com.ssafy.domain.entity.Mate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ApiModel("activity List Response")
public class ActivityListRes extends BaseResponseBody{

    @ApiModelProperty(name = "activity List")
    List<ActivityRes> list;

    long totalPages;
    long totalElements;

    public static ActivityListRes of(long totalPages, long totalElements, List<ActivityRes> mates){



        ActivityListRes activityListRes = new ActivityListRes();

        activityListRes.setTotalPages( totalPages );
        activityListRes.setTotalElements( totalElements );


        activityListRes.setList(mates);

        return activityListRes;
    }

}
