package com.example.UMCChapter4.domain.member.exception.code;

import com.example.UMCChapter4.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    FOUND(HttpStatus.OK,
            "MEMBER200_1",
            "성공적으로 사용자를 조회했습니다."),
    CREATED(HttpStatus.CREATED,
            "MEMBER201_1",
            "회원가입이 성공적으로 완료됐습니다")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}