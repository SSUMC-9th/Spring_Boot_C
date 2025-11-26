package com.example.UMCChapter4.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PageErrorCode implements BaseErrorCode {
    INVALID_PAGE_NUMBER(HttpStatus.BAD_REQUEST,
            "PAGE400_1",
            "잘못된 페이지 번호 요청입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
