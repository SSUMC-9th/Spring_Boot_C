package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.request.MissionRequestDTO;
import com.example.umc9th.domain.mission.dto.response.MissionResponseDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.store.entity.Store;

public class MissionConverter {
    // dto -> entity
    public static Mission toEntity(Long storeId, MissionRequestDTO.createMission dto) {
        return Mission.builder()
                .content(dto.content())
                .store(Store.builder().id(storeId).build())
                .point(dto.point())
                .deadline(dto.deadline())
                .build();
    }

    // entity -> dto
    public static MissionResponseDTO.createMission toDTO(Mission mission) {
        return MissionResponseDTO.createMission
                .builder()
                .createdAt(mission.getCreatedAt())
                .missionId(mission.getId())
                .build();
    }

    // entity -> dto
    public static MissionResponseDTO.memberMissionDTO toMemberMissionDTO(MemberMission memberMission) {
        return MissionResponseDTO.memberMissionDTO
                .builder()
                .memberMissionId(memberMission.getId())
                .missionId(memberMission.getId())
                .status(memberMission.isStatus())
                .build();
    }
}
