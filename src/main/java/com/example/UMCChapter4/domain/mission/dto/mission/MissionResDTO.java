package com.example.UMCChapter4.domain.mission.dto.mission;

import lombok.Builder;

public class MissionResDTO {

    @Builder
    public record MissionCreateDTO(
            Long missionId
    ){}
}
