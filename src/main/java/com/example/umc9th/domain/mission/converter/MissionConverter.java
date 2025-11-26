package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.request.MissionRequestDTO;
import com.example.umc9th.domain.mission.dto.response.MissionResponseDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.response.ReviewResponseDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

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


    // result -> DTO
    public static MissionResponseDTO.MissionPreViewListDTO toMissionPreViewListDTO(
            Page<Mission> result) {

        return MissionResponseDTO.MissionPreViewListDTO.builder()
                .missionList(result.getContent().stream()
                        .map(MissionConverter::toMissionPreViewDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static MissionResponseDTO.MissionPreViewDTO toMissionPreViewDTO(Mission mission) {
        return MissionResponseDTO.MissionPreViewDTO.builder()
                .missionContent(mission.getContent())
                .storeName(mission.getStore().getName())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .createdAt(LocalDate.from(mission.getCreatedAt()))
                .build();
    }

}
