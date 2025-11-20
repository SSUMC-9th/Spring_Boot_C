package com.springboot.umc9th.domain.mission.dto.res;


import lombok.Builder;

public class MissionResDTO {

    @Builder
    public record CreateMissionResDTO(
            Long missionId,
            Long storeId,
            Integer point,
            String missionCondition
    ) {}
}
