package com.example.UMCChapter4.domain.mission.dto.mission;

import com.example.UMCChapter4.domain.mission.dto.membermission.MemberMissionResDTO;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {

    @Builder
    public record MissionCreateDTO(
            Long missionId
    ){}

    @Builder
    public record MissionPreviewListDTO(
            List<MissionResDTO.MissionPreviewDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    @Builder
    public record MissionPreviewDTO(
            String storeName,
            String condition,
            Integer points,
            String deadline,
            LocalDate createdAt
    ){}
}
