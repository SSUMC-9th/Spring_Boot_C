package com.example.UMCChapter4.domain.mission.exception.code;

import com.example.UMCChapter4.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberMissionErrorCode implements BaseErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER_MISSION404_1",
            "사용자 미션을 찾지 못했습니다."),
    DUPLICATED(HttpStatus.BAD_REQUEST,
            "MEMBER_MISSION400_1",
            "이미 진행 중인 미션입니다.");



    private final HttpStatus status;
    private final String code;
    private final String message;
}
