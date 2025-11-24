package com.example.umc9th.domain.review.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    REVIEW_CREATED(HttpStatus.CREATED,
            "Review201",
            "성공적으로 리뷰가 생성되었습니다."
            ),

    REVIEW_FOUND(HttpStatus.OK,
            "REVIEW200_1",
            "성공적으로 리뷰를 조회했습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;

}
