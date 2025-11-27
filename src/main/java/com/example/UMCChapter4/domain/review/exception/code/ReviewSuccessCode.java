package com.example.UMCChapter4.domain.review.exception.code;

import com.example.UMCChapter4.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
            "REVIEW200_1",
            "요청을 성공적으로 처리했습니다."),
    FOUND(HttpStatus.FOUND,
            "REVIEW302_1",
            "성공적으로 리뷰를 조회했습니다."),
    CREATED(HttpStatus.CREATED,
            "REVIEW201_1",
            "리뷰 작성을 성공적으로 완료했습니다")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}