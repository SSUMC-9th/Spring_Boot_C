package com.example.umc9th.domain.mission.service.command;

import com.example.umc9th.domain.mission.dto.request.MissionRequestDto;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import org.springframework.stereotype.Service;

@Service
public interface MissionCommandService {
    MemberMission challengeMission(Long memberId, MissionRequestDto request);
}