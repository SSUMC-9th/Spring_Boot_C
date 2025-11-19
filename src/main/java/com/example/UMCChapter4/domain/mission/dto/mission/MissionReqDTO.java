package com.example.UMCChapter4.domain.mission.dto.mission;

import lombok.Builder;

import java.time.LocalDate;

public class MissionReqDTO {

    @Builder
    public record MissionCreateDTO(
            LocalDate deadline,
            String condition,
            Integer points,
            Long storeId
    ){}
}
