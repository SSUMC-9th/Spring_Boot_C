package com.example.UMCChapter4.domain.mission.exception.code;

import com.example.UMCChapter4.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberMissionSuccessCode implements BaseSuccessCode {

    FOUND(HttpStatus.FOUND,
            "MEMBER_MISSION302_1",
            "성공적으로 사용자 미션을 조회했습니다."),
    CREATED(HttpStatus.CREATED,
            "MEMBER_MISSION201_1",
            "사용자 미션으로 성공적으로 등록했습니다"),
    OK(HttpStatus.OK,
            "MEMBER_MISSION200_1",
            "요청이 성공했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
