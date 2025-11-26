package com.example.UMCChapter4.domain.mission.dto.membermission;

import lombok.Builder;

public class MemberMissionReqDTO {
    @Builder
    public record MemberMissionCreateDTO(
            Long memberId,
            Long missionId
    ){}

    @Builder
    public record MemberMissionCompleteDTO(
            Long memberMissionId
    ){}
}
