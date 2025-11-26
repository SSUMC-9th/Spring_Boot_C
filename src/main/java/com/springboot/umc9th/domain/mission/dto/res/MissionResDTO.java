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
            Integer point,         // 엔티티의 point 매핑
            String missionSpec,    // 엔티티의 mission_condition 매핑
            LocalDate deadline     // LocalDateTime -> LocalDate 변환 예정
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

    // 내가 진행 중인 미션 DTO (가게 이름 포함)
    @Builder
    public record MyMissionDTO(
            Long missionId,
            String storeName,    // 어느 가게인지
            Integer point,       // 보상
            String missionSpec,  // 미션 내용
            LocalDate deadline   // 마감일
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
