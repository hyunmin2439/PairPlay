package com.ssafy.api.controller;

import com.ssafy.api.request.*;
import com.ssafy.api.response.BaseResponseBody;
import com.ssafy.api.response.PlaceDetailRes;
import com.ssafy.api.response.PlaceListRes;
import com.ssafy.api.response.ReservationCheckRes;
import com.ssafy.api.service.PlaceService;
import com.ssafy.domain.document.MyReservation;
import com.ssafy.domain.document.Place;
import com.ssafy.domain.document.PlaceDetail;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.ssafy.common.statuscode.PlaceCode.*;

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

    /**
     * 전체를 조회하여 내부 필드값을 수정이 필요하여 사용
     */
//    @GetMapping
//    @ApiOperation(value = "전체 체육 시설 목록 정보", notes = "<strong>전체 체육 시설 목록</strong>을 넘겨준다.")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "전체 체육 시설 목록 검색에 성공했습니다.", response = PlaceListRes.class),
//            @ApiResponse(code = 500, message = "Server Error.", response = BaseResponseBody.class)
//    })
//    public List<MyReservation> allPlaces() {
//        return null;
//    }
//

    /**
     * 체육시설 조회 API
     */
    @GetMapping("/{placeId}")
    @ApiOperation(value = "체육 시설 상세 정보", notes = "<strong>체육 시설 상세 정보</strong>를 넘겨준다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "체육 시설 상세 조회에 성공했습니다.", response = PlaceListRes.class),
            @ApiResponse(code = 404, message = "체육시설의 정보를 찾을 수 없습니다.", response = PlaceListRes.class),
            @ApiResponse(code = 500, message = "Server Error.", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> getDetailPlace (
            @PathVariable(value = "placeId", required = true) @ApiParam(value = "체육 시설 ID 값", required = true) Long placeId) {

        PlaceDetail placeDetail = placeService.getDetailPlace(placeId);

        return ResponseEntity.status(200).body(PlaceDetailRes.of(SUCCESS_DETAIL_PLACE.getCode(), SUCCESS_DETAIL_PLACE.getMessage(), placeDetail));
    }

    @GetMapping("/recent")
    @ApiOperation(value = "유저가 최근 본 체육 시설 목록 정보", notes = "유저가 <strong>최근 본 체육 시설 목록 정보</strong>를 넘겨준다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "체육 시설 목록 검색에 성공했습니다.", response = PlaceListRes.class),
            @ApiResponse(code = 404, message = "유저가 최근 본 체육시설 정보가 없습니다.", response = PlaceListRes.class),
            @ApiResponse(code = 500, message = "Server Error.", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> getRecentPlaces() {

        List<Place> places = placeService.getRecentViewPlaces();

        return ResponseEntity.status(200).body(
                PlaceListRes.of(
                        SUCCESS_SEARCH_PLACE.getCode(),
                        SUCCESS_SEARCH_PLACE.getMessage(),
                        1,
                        places.size(),
                        places,
                        placeService.getPlaceMemberFromAuthentication().getLikeItems() // 유저가 찜한 체육시설 목록
                )
        );
    }

    @GetMapping("/popular")
    @ApiOperation(value = "유저 주소 근처 인기 있는 체육 시설", notes = "유저의 주소 근처의 <strong>인기 있는 체육 시설 목록</strong>을 넘겨준다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "체육 시설 목록 검색에 성공했습니다.", response = PlaceListRes.class),
            @ApiResponse(code = 500, message = "Server Error.", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> getPopularPlaces() {

        List<Place> places = placeService.getPopularPlaces();

        return ResponseEntity.status(200).body(
                PlaceListRes.of(
                        SUCCESS_SEARCH_PLACE.getCode(),
                        SUCCESS_SEARCH_PLACE.getMessage(),
                        1,
                        places.size(),
                        places,
                        placeService.getPlaceMemberFromAuthentication().getLikeItems() // 유저가 찜한 체육시설 목록
                )
        );
    }

    @PostMapping("/search")
    @ApiOperation(value = "체육 시설 목록 정보", notes = "<strong>검색한 체육 시설 목록</strong>을 페이지로 넘겨준다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "체육 시설 목록 검색에 성공했습니다.", response = PlaceListRes.class),
            @ApiResponse(code = 500, message = "Server Error.", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> searchPlaces(
            @PageableDefault(page = 0, size = 20) Pageable pageable,
            @RequestBody @ApiParam(value = "체육 시설 검색 정보", required = true) PlaceSearchPostReq searchInfo) {

        Page<Place> page = placeService.searchPlaces(pageable, searchInfo);

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

    /**
     * 찜하기 API Put
     */
    @PutMapping("/like/{placeId}")
    @ApiOperation(value = "체육 시설 찜하기", notes = "체육 시설에 대한 <strong>찜 등록/삭제</strong> 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "체육 시설 찜 등록/삭제에 성공했습니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "Server Error.", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> likePlace (
            @PathVariable(value = "placeId", required = true) @ApiParam(value = "체육 시설 ID 값", required = true) Long placeId) {

        placeService.likePlace(placeId);

        // 성공 여부만 내려주면 됨.
        return ResponseEntity.status(200).body(BaseResponseBody.of(SUCCESS_UPDATE_LIKE_PLACE.getCode(), SUCCESS_UPDATE_LIKE_PLACE.getMessage()));
    }

    /**
     * 리뷰 API Post / Put / Delete
     */
    @PostMapping("/review")
    @ApiOperation(value = "체육 시설 리뷰 작성", notes = "체육 시설에 대한 <strong>리뷰를 등록</strong> 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "체육 시설 리뷰 등록에 성공했습니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 400, message = "요청 변수 값이 비어 있습니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 400, message = "체육 시설 이용 이후부터 리뷰를 작성할 수 있습니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "예약한 유저와 로그인 된 유저 정보가 일치하지 않습니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "체육시설의 정보를 찾을 수 없습니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "예약 정보를 찾을 수 없습니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "Server Error.", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> postReview (
            @RequestBody @ApiParam(value = "체육 시설 리뷰 정보", required = true) ReviewPostReq reviewInfo) {

        placeService.postReview(reviewInfo);

        return ResponseEntity.status(200).body(BaseResponseBody.of(SUCCESS_POST_PLACE_REVIEW.getCode(), SUCCESS_POST_PLACE_REVIEW.getMessage()));
    }

    @PutMapping("/review")
    @ApiOperation(value = "체육 시설 리뷰 수정", notes = "체육 시설에 대한 <strong>리뷰를 수정</strong> 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "체육 시설 리뷰 수정에 성공했습니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "리뷰 정보를 찾을 수 없습니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "체육시설의 정보를 찾을 수 없습니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "Server Error.", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> modifyReview (
            @RequestBody @ApiParam(value = "체육 시설 리뷰 ID값과 리뷰 정보", required = true) ReviewPutReq reviewInfo) {

        placeService.modifyReview(reviewInfo);

        return ResponseEntity.status(200).body(BaseResponseBody.of(SUCCESS_MODIFY_PLACE_REVIEW.getCode(), SUCCESS_MODIFY_PLACE_REVIEW.getMessage()));
    }

    @DeleteMapping("/review")
    @ApiOperation(value = "체육 시설 리뷰 삭제", notes = "체육 시설에 대한 <strong>리뷰를 삭제</strong> 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "체육 시설 리뷰 삭제에 성공했습니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "리뷰 정보를 찾을 수 없습니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "체육시설의 정보를 찾을 수 없습니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "예약 정보를 찾을 수 없습니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "Server Error.", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> deleteReview (
            @RequestBody @ApiParam(value = "체육 시설 리뷰 ID값", required = true) ReviewDeleteReq reviewInfo) {

        placeService.deleteReview(reviewInfo);

        return ResponseEntity.status(200).body(BaseResponseBody.of(SUCCESS_DELETE_PLACE_REVIEW.getCode(), SUCCESS_DELETE_PLACE_REVIEW.getMessage()));
    }

    /**
     * 예약 API Post / Delete
     */

    @PostMapping("/reservation/check")
    @ApiOperation(value = "체육 시설 예약 가능 조회", notes = "체육 시설의 해당 날짜에 <strong>예약이 가능한지 조회</strong>한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "체육 시설 예약 가능 조회에 성공했습니다.", response = ReservationCheckRes.class),
            @ApiResponse(code = 404, message = "체육시설의 정보를 찾을 수 없습니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "예약 정보를 찾을 수 없습니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "Server Error.", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> checkReservePlace (
            @RequestBody @ApiParam(value = "체육 시설 예약 정보", required = true) ReservationCheckPostReq reservationInfo) {

        Map<Integer, Integer> times = placeService.checkReservePlace(reservationInfo);

        return ResponseEntity.status(200).body(
                ReservationCheckRes.of(
                        SUCCESS_RESERVE_FOUND.getCode(),
                        SUCCESS_RESERVE_FOUND.getMessage(),
                        times
                )
        );
    }

    @PostMapping("/reservation")
    @ApiOperation(value = "체육 시설 예약 등록", notes = "체육 시설을 <strong>예약 등록</strong>한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "체육 시설 예약 등록에 성공했습니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 400, message = "현재 날짜 보다 이전에는 예약을 등록할 수 없습니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "체육시설의 정보를 찾을 수 없습니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "Server Error.", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> reservePlace (
            @RequestBody @ApiParam(value = "체육 시설 예약 정보", required = true) ReservationPostReq reservationInfo) {

        placeService.reservePlace(reservationInfo);

        return ResponseEntity.status(200).body(BaseResponseBody.of(SUCCESS_RESERVE_PLACE.getCode(), SUCCESS_RESERVE_PLACE.getMessage()));
    }

    @DeleteMapping("/reservation")
    @ApiOperation(value = "체육 시설 예약 취소", notes = "체육 시설을 <strong>예약 취소</strong>한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "체육 시설 예약 취소에 성공했습니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 400, message = "체육 시설 이용 시작 이후에는 예약을 취소할 수 없습니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "예약 정보를 찾을 수 없습니다.", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "Server Error.", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> cancelPlace (
            @RequestBody @ApiParam(value = "체육 시설 예약 ID값", required = true) ReservationDeleteReq reservationInfo) {

        placeService.cancelPlace(reservationInfo);

        return ResponseEntity.status(200).body(BaseResponseBody.of(SUCCESS_CANCEL_PLACE.getCode(), SUCCESS_CANCEL_PLACE.getMessage()));
    }

    /**
     * 유저의 예약 정보를 조회하기 위한 api : 테스트용
     */
//    @GetMapping("reservation/test/{memberId}/{sw}")
//    @ApiOperation(value = "예약 정보", notes = "<strong>유저의 예약 정보</strong>를 넘겨준다. (테스트용: 추후 없어질 API)")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "유저의 예약 정보 검색에 성공했습니다.", response = PlaceListRes.class),
//            @ApiResponse(code = 500, message = "Server Error.", response = BaseResponseBody.class)
//    })
//    public List<MyReservation> testGetReservation(
//            @PathVariable(value = "memberId", required = true) @ApiParam(value = "member ID 값", required = true) Long memberId,
//            @PathVariable(value = "sw", required = true) @ApiParam(value = "시설 이용 종료 시각 기준 0: 전체, 1:사용완료, 2:예약중(사용전이나 사용중)", required = true) int sw
//    ) {
//        List<MyReservation> list = placeService.testGetReservation(memberId, sw);
//        return list;
//    }
}
