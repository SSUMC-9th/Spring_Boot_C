package com.example.UMCChapter4.domain.mission.exception.code;

import com.example.UMCChapter4.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_1", "미션을 찾지 못했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
