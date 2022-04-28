package com.ssafy.api.controller;

import com.ssafy.api.response.PlaceListRes;
import com.ssafy.api.service.ActivityService;
import com.ssafy.domain.entity.Activity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 체육 시설 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "체육 시설 관련 API", tags = {"Places"})
@RestController
@RequestMapping("/api/v1/places")
public class PlaceController {
    
    @GetMapping()
    @ApiOperation(value = "체육 시설 목록 정보", notes = "<strong>전체 체육 시설 목록</strong>을 넘겨준다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = PlaceListRes.class),
    })
    public ResponseEntity<? extends Page<PlaceListRes>> placeList(@PageableDefault(page = 0, size = 8) Pageable pageable) {

        return ResponseEntity.status(200).body( null );
    }
}
