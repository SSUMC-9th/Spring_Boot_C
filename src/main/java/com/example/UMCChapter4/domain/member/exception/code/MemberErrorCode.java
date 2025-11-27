package com.example.UMCChapter4.domain.member.exception.code;

import com.example.UMCChapter4.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER404_1",
            "해당 사용자를 찾지 못했습니다."),
    DUPLICATED(HttpStatus.BAD_REQUEST,
            "MEMBER400_1",
            "중복된 이름입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
