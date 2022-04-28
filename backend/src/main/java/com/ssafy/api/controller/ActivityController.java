package com.ssafy.api.controller;


import com.ssafy.api.request.ActivityPostReq;
import com.ssafy.api.response.ActivityRes;
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


@Api(value = "메이트 API", tags = {"MATE"})
@RestController
@RequestMapping("/api/v1/mates")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }


    //공고 전체조회
    @GetMapping()
    @ApiOperation(value = "공고 전체 조회", notes = "공고를 <strong>전체조회</strong>한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = BaseResponseBody.class),
    })
    public ResponseEntity<Page<ActivityRes>> getActivityList(@PageableDefault(page = 0, size = 6) Pageable pageable){

        Page<Activity> activities = activityService.getAvtivityList(pageable);

        if(activities != null){
            return ResponseEntity.status(500).body(ActivityListRes.of(activities));
        }else {
            return ResponseEntity.status(500).body(null);
        }
    }



//    //공고 카테고리별 조회
//    @PostMapping()
//    @ApiOperation(value = "공고 카테고리별 조회", notes = "공고를 <strong>카테고리별 조회</strong>한다")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Success", response = BaseResponseBody.class),
//    })
//    public ResponseEntity<ActivityRes> getCategory(@RequestBody @ApiParam(value = "카테고리") //ActivityCategoryReq
//                                                   // ){
//
//    }


    //공고 상세조회
    @GetMapping("/{activityId}")
    @ApiOperation(value = "공고 상세 조회", notes = "공고 <string>상세조회</strong>한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = BaseResponseBody.class),
    })
    public ResponseEntity<ActivityRes> getAvtivityDetail(@PathVariable(name = "activityId") @ApiParam(value="메이트 공고 상세번호", required = true)Long activityId){
        Activity activity = activityService.getAvtivityDetail(activityId);

        if(activity != null){
            return ResponseEntity.status(200).body(ActivityRes.of(activity));
        }else{
            return ResponseEntity.status(500).body(null);
        }

    }




    //공고 생성
    @PostMapping()
    @ApiOperation(value = "공고 생성", notes = "공고를 <strong>생성</strong>한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = BaseResponseBody.class),
    })
    public ResponseEntity<BaseResponseBody> createActivity(@RequestBody @ApiParam(value = "공고 정보", required = true) ActivityPostReq activityInfo) {


        int statusCode = activityService.createActivity(activityInfo);


        return createResponseEntityToStatusCode(statusCode);
    }
//이미지 일단 생략






    private ResponseEntity<BaseResponseBody> createResponseEntityToStatusCode(int statusCode) {
        switch (statusCode) {
            case 200:
                return ResponseEntity.status(200).body(BaseResponseBody.of(200, "SUCCESS"));
            case 401:
                return ResponseEntity.status(401).body(BaseResponseBody.of(401, "ACCESS DENIED"));
            default:
                return ResponseEntity.status(500).body(BaseResponseBody.of(500, "FAIL"));
        }
    }


}
