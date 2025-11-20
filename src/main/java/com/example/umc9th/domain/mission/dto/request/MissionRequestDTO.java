package com.example.umc9th.domain.mission.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class MissionRequestDTO {
    public record createMission(
            @NotBlank(message = "미션 내용은 필수입니다.")
            String content,
            @NotNull(message = "미션 포인트는 필수입니다.")
            int point,
            @NotNull(message = "미션 마감일은 필수입니다.")
            LocalDateTime deadline
    ){}
}
