package com.example.umc9th.domain.member.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_1", "해당 회원을 찾을 수 없습니다."),
    MEMBER_INVALID(HttpStatus.BAD_REQUEST, "MEMBER400_1", "유효하지 않은 회원 정보입니다."),
    ;
    private final HttpStatus status;
    private final String code;
    private final String message;

}
