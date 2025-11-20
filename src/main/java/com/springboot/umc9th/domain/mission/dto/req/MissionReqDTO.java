package com.springboot.umc9th.domain.mission.dto.req;

import lombok.Builder;

import java.time.LocalDateTime;

public class MissionReqDTO {

    @Builder
    public record CreateDTO(
            Integer point,
            LocalDateTime deadline,
            String missionCondition
    ) {}
}