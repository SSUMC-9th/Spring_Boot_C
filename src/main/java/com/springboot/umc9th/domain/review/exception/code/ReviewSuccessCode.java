package com.springboot.umc9th.domain.review.exception.code;

import com.springboot.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {
    CREATED(HttpStatus.CREATED,
            "Review200_1",
            "성공적으로 리뷰를 생성했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
