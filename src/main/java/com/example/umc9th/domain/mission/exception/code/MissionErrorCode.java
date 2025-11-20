package com.example.umc9th.domain.mission.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_1", "존재하지 않는 미션입니다."),
    MISSION_ALREADY_CHALLENGED(HttpStatus.BAD_REQUEST, "MISSION400_1", "이미 도전 중인 미션입니다."),
    MISSION_COMPLETED_ALREADY(HttpStatus.BAD_REQUEST, "MISSION400_2", "이미 완료된 미션입니다."),
    MISSION_EXPIRED(HttpStatus.BAD_REQUEST, "MISSION400_3", "기간이 지나 도전할 수 없는 미션입니다.");
    ;
    private final HttpStatus status;
    private final String code;
    private final String message;

}
