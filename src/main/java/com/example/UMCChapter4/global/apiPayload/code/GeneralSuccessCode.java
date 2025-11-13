package com.example.UMCChapter4.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode{

    OK(HttpStatus.OK,
            "COMMON200_1",
            "요청이 성공했습니다."),

    CREATED(HttpStatus.CREATED,
            "CREATE201_1",
            "새로운 리소스가 생성되었습니다."),

    ACCEPTED(HttpStatus.ACCEPTED,
            "COMMON202_1",
            "요청이 성공적으로 접수되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
