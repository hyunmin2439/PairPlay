package com.ssafy.api.controller;


import com.ssafy.api.request.ActivityCategoryReq;
import com.ssafy.api.request.ActivityPostReq;
import com.ssafy.api.request.ActivityRegisterReq;
import com.ssafy.api.response.ActivityDetailRes;
import com.ssafy.api.response.BaseResponseBody;
import com.ssafy.api.response.ActivityListRes;
import com.ssafy.api.service.ActivityService;
import com.ssafy.domain.entity.Activity;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ssafy.common.statuscode.ActivityCode.*;


@Api(value = "메이트 API", tags = {"MATE"})
@RestController
@RequestMapping("/api/v1/mates")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }


    /**
     * 공고 전체조회
     */
    @GetMapping()
    @ApiOperation(value = "공고 전체 조회", notes = "공고를 <strong>전체조회</strong>한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = BaseResponseBody.class),
    })
    public ResponseEntity<? extends ActivityListRes> getActivityList(@PageableDefault(page = 0, size = 8) Pageable pageable){

        Page<Activity> activities = activityService.getActivityList(pageable);

        return ResponseEntity.status(200).body(ActivityListRes.of(activities, SUCCESS_MATE_LIST.getCode(), SUCCESS_MATE_LIST.getMessage()));

    }


    /**
     * 공고 카테고리별조회
     */
    @PostMapping()
    @ApiOperation(value = "공고 카테고리 조회", notes = "공고를 <strong>카테고리별로 조회</strong>한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = BaseResponseBody.class),
    })
    public ResponseEntity<? extends ActivityListRes> getCategoryList(@RequestBody @ApiParam(value = "카테고리") ActivityCategoryReq activityCategoryReq, @PageableDefault(page = 0, size = 8) Pageable pageable){


        Page<Activity> activities = activityService.getCategoryList(activityCategoryReq, pageable);

        return ResponseEntity.status(200).body(ActivityListRes.of(activities, SUCCESS_MATE_LIST.getCode(), SUCCESS_MATE_LIST.getMessage()));
    }


    /**
     * 공고 상세조회
     */
    @GetMapping("/{activityId}")
    @ApiOperation(value = "공고 상세 조회", notes = "공고 <string>상세조회</strong>한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = BaseResponseBody.class),
    })
    public ResponseEntity<? extends ActivityDetailRes> getActivityDetail(@PathVariable(name = "activityId") @ApiParam(value="메이트 공고 상세번호", required = true)Long activityId){
        Activity activity = activityService.getActivityDetail(activityId);


        return ResponseEntity.status(200).body(ActivityDetailRes.of(activity, SUCCESS_GET_DETAIL.getCode(), SUCCESS_GET_DETAIL.getMessage()));
    }




    /**
     * 공고 생성
     */
    @PostMapping("/mate")
    @ApiOperation(value = "공고 생성", notes = "공고를 <strong>생성</strong>한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공고 생성 성공하였습니다.", response = BaseResponseBody.class),
    })
    public ResponseEntity<BaseResponseBody> createActivity(@RequestBody @ApiParam(value = "공고 정보", required = true) ActivityPostReq activityInfo) {


        activityService.createActivity(activityInfo);


        return ResponseEntity.status(200).body(BaseResponseBody.of(SUCCESS_ACTIVITY_CREATE.getCode(), SUCCESS_ACTIVITY_CREATE.getMessage()));
    }
//이미지 일단 생략

    /**
     * 공고 신청
     */
    @PostMapping("/register")
    @ApiOperation(value = "공고 신청", notes = "공고를 <strong>신청</strong>한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공고 신청 성공하였습니다.", response = BaseResponseBody.class),
    })
    public ResponseEntity<BaseResponseBody> registeActivity(@RequestBody @ApiParam(value = "공고 정보", required = true) ActivityRegisterReq req) {

        activityService. registerActivity(req);
        
        return ResponseEntity.status(200).body(BaseResponseBody.of(SUCCESS_ACTIVITY_REGISTER.getCode(), SUCCESS_ACTIVITY_REGISTER.getMessage()));
    }





}
