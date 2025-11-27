package com.springboot.umc9th.domain.mission.dto.res;


import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {

    @Builder
    public record CreateMissionResDTO(
            Long missionId,
            Long storeId,
            Integer point,
            String missionCondition
    ) {}

    @Builder
    public record MissionDTO(
            Long missionId,
            Integer point,
            String missionSpec,
            LocalDate deadline
    ){}

    @Builder
    public record MissionPreViewListDTO(
            List<MissionDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    // 내가 진행 중인 미션 DTO
    @Builder
    public record MyMissionDTO(
            Long missionId,
            String storeName,
            Integer point,
            String missionSpec,
            LocalDate deadline
    ){}

    @Builder
    public record MyMissionPreViewListDTO(
            List<MyMissionDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

}
