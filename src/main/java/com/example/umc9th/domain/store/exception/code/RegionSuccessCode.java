package com.example.umc9th.domain.store.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum RegionSuccessCode implements BaseSuccessCode {
    FOUND(HttpStatus.OK,
            "REGION200_1",
            "성공적으로 지역을 조회했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
