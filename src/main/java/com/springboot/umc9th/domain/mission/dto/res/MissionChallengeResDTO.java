package com.springboot.umc9th.domain.mission.dto.res;

import lombok.Builder;

public class MissionChallengeResDTO {

    @Builder
    public record ChallengeResDTO(
            Long memberMissionId,
            Long missionId,
            Long memberId){}
}