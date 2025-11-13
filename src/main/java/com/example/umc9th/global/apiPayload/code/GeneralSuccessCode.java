package com.example.umc9th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {


    OK(HttpStatus.OK,
            "COMMON2000",
            "요청에 성공하였습니다."),

    CREATED(HttpStatus.CREATED,
            "COMMON2010",
            "요청에 성공하였으며, 리소스가 정상적으로 생성되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}

