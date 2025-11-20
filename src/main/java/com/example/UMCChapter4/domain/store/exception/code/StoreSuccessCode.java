package com.example.UMCChapter4.domain.store.exception.code;

import com.example.UMCChapter4.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {

    FOUND(HttpStatus.OK,
            "STORE200_1",
            "성공적으로 매장을 조회했습니다."),
    CREATED(HttpStatus.CREATED,
            "STORE201_1",
            "매장 등록을 성공적으로 완료됐습니다")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
