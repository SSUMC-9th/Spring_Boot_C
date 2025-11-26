package com.example.UMCChapter4.domain.mission.dto.membermission;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MemberMissionResDTO {

    @Builder
    public record MemberMissionInProgressDTO(
            Long memberMissionId
    ){}

    @Builder
    public record MemberMissionCompleteDTO(
            String storeName,
            Integer points,
            String description,
            String status,

            MemberMissionStatusListDTO inProgressList
    ){}

    @Builder
    public record MemberMissionStatusListDTO(
            List<MemberMissionStatusDTO> memberMissionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    @Builder
    public record MemberMissionStatusDTO(
            String storeName,
            String condition,
            Integer points,
            String status
//            LocalDate createdAt
    ){}


}
