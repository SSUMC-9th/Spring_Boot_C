package com.springboot.umc9th.domain.mission.exception.code;

import com.springboot.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    CHALLENGE_CREATED(HttpStatus.CREATED,
            "Mission200_1",
            "새로운 유저 미션이 등록되었습니다."),
    MISSION_CREATED(HttpStatus.CREATED,
            "Mission201_2",
            "가게에 새로운 미션을 추가했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
