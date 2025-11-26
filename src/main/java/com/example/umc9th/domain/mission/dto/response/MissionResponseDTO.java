package com.example.umc9th.domain.mission.dto.response;

import com.example.umc9th.domain.review.dto.response.ReviewResponseDTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResponseDTO {

    @Builder
    public record createMission(
            Long missionId,
            LocalDateTime createdAt
    ) {
    }

    @Builder
    public record memberMissionDTO(
            Long memberMissionId,
            Long missionId,
            boolean status
    ){}

    // 미션 정보들의 목록
    // 리뷰 정보들의 목록
    @Builder
    public record MissionPreViewListDTO(
            List<MissionResponseDTO.MissionPreViewDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record MissionPreViewDTO(
            String storeName,
            String missionContent,
            Integer point,
            LocalDateTime deadline,
            LocalDate createdAt
    ){}




}
