package com.example.UMCChapter4.domain.review.exception.code;

import com.example.UMCChapter4.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {
    // For test
    REVIEW_EXCEPTION(HttpStatus.BAD_REQUEST, "REVIEW400_1", "리뷰 예외 발생"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
