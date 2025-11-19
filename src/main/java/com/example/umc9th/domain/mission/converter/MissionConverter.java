package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.response.MissionResponseDto;
import com.example.umc9th.domain.mission.entity.enums.EStatus;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.member.entity.Member;

import com.example.umc9th.domain.mission.entity.Mission;


import java.time.LocalDateTime;

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
}