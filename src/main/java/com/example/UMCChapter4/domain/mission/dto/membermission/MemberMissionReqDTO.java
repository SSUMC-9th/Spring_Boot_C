package com.example.UMCChapter4.domain.mission.dto.membermission;

import lombok.Builder;

public class MemberMissionReqDTO {
    @Builder
    public record MemberMissionInProgressDTO(
            Long missionId
    ){}
}
