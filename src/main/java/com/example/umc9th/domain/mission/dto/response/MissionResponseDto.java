package com.example.umc9th.domain.mission.dto.response;

import com.example.umc9th.domain.mission.entity.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class MissionResponseDto {

    private Long missionId;
    private String storeName;
    private Long point;
    private String content;
    private EStatus status;

    public record JoinResultDTO(Long memberMissionId, LocalDateTime createdAt) {
    }

    // 미션 목록 조회 (페이징 포함) DTO
    @Builder
    public record MissionPreViewListDTO(
            List<MissionPreViewDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    // 단일 미션 DTO
    @Builder
    public record MissionPreViewDTO(
            Long missionId,
            String storeName,
            Long point,
            LocalDateTime dueDate,
            String content,
            EStatus status
    ){}
}
