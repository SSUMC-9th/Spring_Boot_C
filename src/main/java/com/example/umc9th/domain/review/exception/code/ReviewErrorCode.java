package com.example.umc9th.domain.review.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {
    INVALID_REVIEW_REQUEST(HttpStatus.BAD_REQUEST, "REVIEW400_1", "리뷰 요청 값이 올바르지 않습니다."),
    ;
    private final HttpStatus status;
    private final String code;
    private final String message;

}
