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

    // 미션 목록 조회
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
                .storeName(mission.getStore().getName())
                .point(mission.getPoint())
                .dueDate(mission.getDueDate())
                .content(mission.getContent())
                .build();
    }

    // MemberMission -> MissionPreviewDTO로 변환 (오버로딩)
    public static MissionResponseDto.MissionPreViewDTO toMissionPreViewDTO(MemberMission memberMission) {
        return MissionResponseDto.MissionPreViewDTO.builder()
                .missionId(memberMission.getMission().getId())
                .storeName(memberMission.getMission().getStore().getName())
                .point(memberMission.getMission().getPoint())
                .dueDate(memberMission.getMission().getDueDate())
                .content(memberMission.getMission().getContent())
                .build();
    }
    // Page 객체로 매개변수를 받는 메서드는 오버로딩 불가(같은 매개변수 취급) -> 메서드명 변경
    public static MissionResponseDto.MissionPreViewListDTO toMissionPreViewListDTOFromMemberMission(
            Page<MemberMission> memberMissionPage
    ) {
        List<MissionResponseDto.MissionPreViewDTO> missionList = memberMissionPage.stream()
                .map(MissionConverter::toMissionPreViewDTO)
                .collect(Collectors.toList());

        return MissionResponseDto.MissionPreViewListDTO.builder()
                .missionList(missionList)
                .listSize(missionList.size())
                .totalPage(memberMissionPage.getTotalPages())
                .totalElements(memberMissionPage.getTotalElements())
                .isFirst(memberMissionPage.isFirst())
                .isLast(memberMissionPage.isLast())
                .build();
    }



}