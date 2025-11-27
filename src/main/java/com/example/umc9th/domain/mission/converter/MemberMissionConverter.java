package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.request.MissionRequestDTO;
import com.example.umc9th.domain.mission.dto.response.MemberMissionResponseDTO;
import com.example.umc9th.domain.mission.dto.response.MissionResponseDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public class MemberMissionConverter {

    // result -> DTO
    public static MemberMissionResponseDTO.MemberMissionPreViewListDTO toMissionPreViewListDTO(
            Page<MemberMission> result) {

        return MemberMissionResponseDTO.MemberMissionPreViewListDTO.builder()
                .missionList(result.getContent().stream()
                        .map(MemberMissionConverter::toMissionPreViewDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static MemberMissionResponseDTO.MemberMissionPreViewDTO toMissionPreViewDTO(MemberMission memberMission) {
        return MemberMissionResponseDTO.MemberMissionPreViewDTO.builder()
                .id(memberMission.getId())
                .status(memberMission.isStatus())
                .dto(MissionConverter.toMissionPreViewDTO(memberMission.getMission()))
                .build();
    }

}
