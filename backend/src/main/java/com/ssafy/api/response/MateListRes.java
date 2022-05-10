package com.ssafy.api.response;


import com.ssafy.domain.entity.Mate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ApiModel("Mate List Response")
public class MateListRes extends BaseResponseBody{

//    @ApiModelProperty(name = "mate list")
//    Page<Mate> list;
//
//    public static MateListRes of(Page<Mate> mateList, Integer statusCode, String message){
//
//        MateListRes mateListRes = new MateListRes();
//
//
//        mateListRes.setCode(statusCode);
//        mateListRes.setMessage(message);
//
//        List<MateListRes> list = new ArrayList<>();
//
//        Pageable pageable = mateList.getPageable();
//        long total = mateList.getTotalElements();
//
//        mateList.forEach(mate -> {
////            list.add(MateRes.of(mate));
//        });
//
////        mateListRes.setList(new PageImpl<MateRes>(list, pageable, total));
//
//        return mateListRes;
//    }
//


}
