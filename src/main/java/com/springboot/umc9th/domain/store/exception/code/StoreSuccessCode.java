package com.springboot.umc9th.domain.store.exception.code;

import com.springboot.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {
    STORE_CREATED(HttpStatus.CREATED,
            "STORE200_1",
            "새로운 가게를 추가했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
