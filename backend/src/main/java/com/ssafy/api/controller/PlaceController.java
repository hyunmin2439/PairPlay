package com.ssafy.api.controller;

import com.ssafy.api.request.PlaceSearchPostReq;
import com.ssafy.api.response.BaseResponseBody;
import com.ssafy.api.response.PlaceListRes;
import com.ssafy.api.service.PlaceService;
import com.ssafy.domain.document.Place;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ssafy.common.statuscode.PlaceCode.SUCCESS_SEARCH_PLACE;
import static com.ssafy.common.statuscode.PlaceCode.SUCCESS_UPDATE_LIKE_PLACE;

/**
 * 체육 시설 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "체육 시설 관련 API", tags = {"Places"})
@RestController
@RequestMapping("/api/v1/places")
public class PlaceController {

    private final PlaceService placeService;
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/{placeId}")
    @ApiOperation(value = "체육 시설 상세 정보", notes = "<strong>체육 시설 상세 정보</strong>를 넘겨준다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "체육 시설 목록 검색에 성공했습니다.", response = PlaceListRes.class),
            @ApiResponse(code = 500, message = "Server Error.", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> getDetailPlace (
            @PathVariable(value = "placeId", required = true) @ApiParam(value = "체육 시설 ID 값", required = true) Long placeId) {


        return ResponseEntity.status(200).body(
                null
        );
    }
    
    @PostMapping("/search")
    @ApiOperation(value = "체육 시설 목록 정보", notes = "<strong>전체 체육 시설 목록</strong>을 넘겨준다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "체육 시설 목록 검색에 성공했습니다.", response = PlaceListRes.class),
            @ApiResponse(code = 500, message = "Server Error.", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> searchPlace(
            @PageableDefault(page = 0, size = 20) Pageable pageable,
            @RequestBody @ApiParam(value = "체육 시설 검색 정보", required = true) PlaceSearchPostReq searchInfo) {

        Page<Place> page = placeService.searchPlace(pageable, searchInfo);

        return ResponseEntity.status(200).body(
                PlaceListRes.of(
                        SUCCESS_SEARCH_PLACE.getCode(),
                        SUCCESS_SEARCH_PLACE.getMessage(),
                        page.getTotalPages(),
                        page.getTotalElements(),
                        page.getContent(),
                        placeService.getPlaceMemberFromAuthentication().getLikeItems() // 유저가 찜한 체육시설 목록
                )
        );
    }

    @PutMapping("/like/{placeId}")
    @ApiOperation(value = "체육 시설 찜하기", notes = "체육 시설에 대한 <strong>찜 등록/삭제</strong> 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "찜 등록/삭제에 성공했습니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "Server Error.", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> likePlace (
            @PathVariable(value = "placeId", required = true) @ApiParam(value = "체육 시설 ID 값", required = true) Long placeId) {

        placeService.likePlace(placeId);

        // 성공 여부만 내려주면 됨.
        return ResponseEntity.status(200).body(BaseResponseBody.of(SUCCESS_UPDATE_LIKE_PLACE.getCode(), SUCCESS_UPDATE_LIKE_PLACE.getMessage()));
    }

    @PostMapping("/review")
    @ApiOperation(value = "체육 시설 리뷰 작성", notes = "체육 시설에 대한 <strong>리뷰를 등록</strong> 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "찜 등록/삭제에 성공했습니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "Server Error.", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> postReview (
            @PathVariable(value = "placeId", required = true) @ApiParam(value = "체육 시설 ID 값", required = true) Long placeId) {

        placeService.likePlace(placeId);

        // 성공 여부만 내려주면 됨.
        return ResponseEntity.status(200).body(BaseResponseBody.of(SUCCESS_UPDATE_LIKE_PLACE.getCode(), SUCCESS_UPDATE_LIKE_PLACE.getMessage()));
    }

    @PutMapping("/review")
    @ApiOperation(value = "체육 시설 리뷰 작성", notes = "체육 시설에 대한 <strong>리뷰를 수정</strong> 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "찜 등록/삭제에 성공했습니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "Server Error.", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> modifyReview (
            @PathVariable(value = "placeId", required = true) @ApiParam(value = "체육 시설 ID 값", required = true) Long placeId) {

        placeService.likePlace(placeId);

        // 성공 여부만 내려주면 됨.
        return ResponseEntity.status(200).body(BaseResponseBody.of(SUCCESS_UPDATE_LIKE_PLACE.getCode(), SUCCESS_UPDATE_LIKE_PLACE.getMessage()));
    }

    @DeleteMapping("/review")
    @ApiOperation(value = "체육 시설 리뷰 작성", notes = "체육 시설에 대한 <strong>리뷰를 삭제</strong> 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "찜 등록/삭제에 성공했습니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "Server Error.", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> deleteReview (
            @PathVariable(value = "placeId", required = true) @ApiParam(value = "체육 시설 ID 값", required = true) Long placeId) {

        placeService.likePlace(placeId);

        // 성공 여부만 내려주면 됨.
        return ResponseEntity.status(200).body(BaseResponseBody.of(SUCCESS_UPDATE_LIKE_PLACE.getCode(), SUCCESS_UPDATE_LIKE_PLACE.getMessage()));
    }
}
