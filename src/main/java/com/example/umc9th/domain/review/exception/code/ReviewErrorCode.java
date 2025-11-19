package com.example.umc9th.domain.review.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND,
            "REVIEW404_1",
            "리뷰가 존재하지 않습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}
