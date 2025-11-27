package com.example.umc9th.domain.mission.dto.response;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MemberMissionResponseDTO {

    // 미션 정보들의 목록
    @Builder
    public record MemberMissionPreViewListDTO(
            List<MemberMissionResponseDTO.MemberMissionPreViewDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record MemberMissionPreViewDTO(
            Long id, // memberMission의 id
            Boolean status,
            MissionResponseDTO.MissionPreViewDTO dto
    ){}




}
