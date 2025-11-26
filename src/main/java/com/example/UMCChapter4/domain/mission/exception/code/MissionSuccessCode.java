package com.example.UMCChapter4.domain.mission.exception.code;

import com.example.UMCChapter4.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    FOUND(HttpStatus.FOUND,
            "MISSION302_1",
            "성공적으로 미션을 조회했습니다."),
    CREATED(HttpStatus.CREATED,
            "MISSION201_1",
            "미션을 성공적으로 등록했습니다"),
    OK(HttpStatus.OK,
            "MISSION200_1",
            "요청을 성공적으로 처리했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}