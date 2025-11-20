package com.example.UMCChapter4.domain.store.exception.code;

import com.example.UMCChapter4.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum LocationErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "LOCATION404_1",
            "해당 지역을 찾지 못했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
