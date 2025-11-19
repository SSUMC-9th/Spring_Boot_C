package com.example.umc9th.domain.mission.dto;

import com.example.umc9th.domain.mission.entity.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MissionResponseDto {
    private Long memberMissionId;
    private String storeName;
    private Long missionPoint;
    private String missionContent;
    private EStatus missionStatus;

}
