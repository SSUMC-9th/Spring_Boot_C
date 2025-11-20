package com.example.UMCChapter4.domain.mission.dto.membermission;

import lombok.Builder;

public class MemberMissionResDTO {

    @Builder
    public record MemberMissionInProgressDTO(
            Long memberMissionId
    ){}
}
