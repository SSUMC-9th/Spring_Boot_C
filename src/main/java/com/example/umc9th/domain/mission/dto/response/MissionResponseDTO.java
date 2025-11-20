package com.example.umc9th.domain.mission.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

public class MissionResponseDTO {

    @Builder
    public record createMission(
            Long missionId,
            LocalDateTime createdAt
    ) {
    }

    @Builder
    public record memberMissionDTO(
            Long memberMissionId,
            Long missionId,
            boolean status
    ){}


}
