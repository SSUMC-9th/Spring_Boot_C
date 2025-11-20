package com.springboot.umc9th.domain.store.exception.code;

import com.springboot.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {

    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE404_1", "가게를 찾을 수 없습니다."),
    LOCAL_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE404_2", "지역(Local)을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}