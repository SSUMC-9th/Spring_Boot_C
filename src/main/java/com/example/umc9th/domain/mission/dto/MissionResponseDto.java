package com.example.umc9th.domain.mission.dto;

import com.example.umc9th.domain.mission.entity.enums.EStatus;
import lombok.Data;

@Data
public class MissionResponseDto {
    private Long memberMissionId;
    private String storeName;
    private Long missionPoint;
    private String missionContent;
    private EStatus missionStatus;

    public MissionResponseDto(Long memberMissionId, String storeName, Long missionPoint, String missionContent, EStatus status) {
        this.memberMissionId = memberMissionId;
        this.storeName = storeName;
        this.missionPoint = missionPoint;
        this.missionContent = missionContent;
        this.missionStatus = status;
    }
}
