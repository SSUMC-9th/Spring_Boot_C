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
            "성공적으로 미션을 도전했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
