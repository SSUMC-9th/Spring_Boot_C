package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.response.MissionResponseDto;
import com.example.umc9th.domain.mission.entity.enums.EStatus;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.member.entity.Member;

import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    // Entity -> DTO
    public static MissionResponseDto.JoinResultDTO toJoinResultDTO(MemberMission memberMission) {
        return new MissionResponseDto.JoinResultDTO(
                memberMission.getId(),
                LocalDateTime.now()
        );
    }

    // DTO -> Entity
    public static MemberMission toEntity(Member member, Mission mission) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(EStatus.IN_PROGRESS) // 진행 중인 mission으로 반환
                .build();
    }

    // 가게별 미션 조회
    // Page<Mission> -> MissionPreViewListDTO
    public static MissionResponseDto.MissionPreViewListDTO toMissionPreViewListDTO(Page<Mission> missionPage) {
        List<MissionResponseDto.MissionPreViewDTO> missionList = missionPage.stream()
                .map(MissionConverter::toMissionPreViewDTO)
                .collect(Collectors.toList());

        return MissionResponseDto.MissionPreViewListDTO.builder()
                .missionList(missionList)
                .listSize(missionList.size())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .build();
    }
    // Mission -> MissionPreViewDTO
    public static MissionResponseDto.MissionPreViewDTO toMissionPreViewDTO(Mission mission) {
        return MissionResponseDto.MissionPreViewDTO.builder()
                .missionId(mission.getId())
                .point(mission.getPoint())
                .dueDate(mission.getDueDate())
                .content(mission.getContent())
                .build();
    }



}