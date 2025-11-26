package com.example.UMCChapter4.domain.review.exception.code;

import com.example.UMCChapter4.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    BAD_REQUEST(
            HttpStatus.BAD_REQUEST,
            "REVIEW400_1",
            "잘못된 접근입니다."),
    NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "REVIEW404_1",
            "리뷰를 찾지 못했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
