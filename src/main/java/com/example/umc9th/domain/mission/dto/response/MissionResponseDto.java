package com.example.umc9th.domain.mission.dto.response;

import com.example.umc9th.domain.mission.entity.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MissionResponseDto {
    private Long memberMissionId;
    private String storeName;
    private Long missionPoint;
    private String missionContent;
    private EStatus missionStatus;

    public record JoinResultDTO(Long memberMissionId, LocalDateTime createdAt) {
    }
}
