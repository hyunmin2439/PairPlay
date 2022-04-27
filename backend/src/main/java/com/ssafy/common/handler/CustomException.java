package com.ssafy.common.handler;

import com.ssafy.common.statuscode.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException {
    private final StatusCode errorCode;
}
