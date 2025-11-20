package com.example.umc9th.domain.mission.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MissionRequestDto {

    @NotNull(message = "미션 ID는 필수입니다.")
    private Long missionId;
}