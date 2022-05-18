package com.ssafy.api.controller;



import com.ssafy.api.request.ActivityCategoryReq;
import com.ssafy.api.request.ActivityPostReq;
import com.ssafy.api.request.ActivityRegisterReq;
import com.ssafy.api.response.*;
import com.ssafy.api.service.ActivityService;
import com.ssafy.api.service.S3FileUploadService;
import com.ssafy.common.handler.CustomException;
import com.ssafy.domain.entity.Activity;
import io.swagger.annotations.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.ssafy.common.statuscode.ActivityCode.*;


@Api(value = "메이트 API", tags = {"MATE"})
@RestController
@RequestMapping("/api/v1/mates")
public class ActivityController {

    private final ActivityService activityService;
    private final S3FileUploadService s3FileUploadService;


    public ActivityController(ActivityService activityService, S3FileUploadService s3FileUploadService) {
        this.activityService = activityService;
        this.s3FileUploadService = s3FileUploadService;
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

        ActivityListRes res = activityService.getActivityList(pageable);

        res.setCode(SUCCESS_MATE_LIST.getCode());
        res.setMessage(SUCCESS_MATE_LIST.getMessage());

        return ResponseEntity.status(200).body(res);

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


        ActivityListRes activities = activityService.getCategoryList(activityCategoryReq, pageable);

        return ResponseEntity.status(200).body(activities);
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
        ActivityDetailRes res = activityService.getActivityDetail(activityId);

        res.setCode(SUCCESS_GET_DETAIL.getCode());
        res.setMessage(SUCCESS_GET_DETAIL.getMessage());

        return ResponseEntity.status(200).body(res);
    }


    /**
     * 공고 생성
     */
    @PostMapping("/mate")
    @ApiOperation(value = "공고 생성", notes = "공고를 <strong>생성</strong>한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공고 생성 성공하였습니다.", response = BaseResponseBody.class),
    })
    public ResponseEntity<? extends BaseResponseBody> createActivity(@RequestBody @ApiParam(value = "공고 정보", required = true) ActivityPostReq activityInfo) {
//                                                                     @RequestPart("imgUrl") @ApiParam(value = "공고 이미지 업로드 List", required = true)List<MultipartFile> multipartFile

//        List<String> fileName = new ArrayList<>();
//
//        if(multipartFile.size() != 0){
//
//            multipartFile.forEach(image -> {
//                try {
//                    fileName.add(s3FileUploadService.upload(image));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    throw new CustomException(FAIL_MATE_IMAGE_S3_UPLOAD_ERROR);
//                }
//            });
//
//        }

        activityService.createActivity(activityInfo);


        return ResponseEntity.status(200).body(BaseResponseBody.of(SUCCESS_ACTIVITY_CREATE.getCode(), SUCCESS_ACTIVITY_CREATE.getMessage()));
    }




    /**
     * 공고 신청
     */
    @PostMapping("/register")
    @ApiOperation(value = "메이트 신청", notes = "메이트를 <strong>신청</strong>한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "메이트 신청 성공하였습니다.", response = BaseResponseBody.class),
    })
    public ResponseEntity<BaseResponseBody> registerActivity(@RequestBody @ApiParam(value = "공고 정보", required = true) ActivityRegisterReq req) {

        activityService.registerActivity(req);

        return ResponseEntity.status(200).body(BaseResponseBody.of(SUCCESS_ACTIVITY_REGISTER.getCode(), SUCCESS_ACTIVITY_REGISTER.getMessage()));
    }

    /**
     * 공고 좋아요/취소
     */
    @PutMapping("/like/{activityId}")
    @ApiOperation(value = "찜하기", notes = "메이트 <strong>공고 찜하기</strong>")
    @ApiResponses({
            @ApiResponse(code = 200, message = "메이트 찜하기/취소 성공하였습니다.", response = BaseResponseBody.class),
    })
    public ResponseEntity<BaseResponseBody> likeActivity(@PathVariable(name = "activityId") @ApiParam(value="메이트 공고 상세번호", required = true)Long activityId){


        activityService.likeActivity(activityId);


        return ResponseEntity.status(200).body(BaseResponseBody.of(SUCCESS_ACTIVITY_LIKE.getCode(), SUCCESS_ACTIVITY_LIKE.getMessage()));
    }






}
