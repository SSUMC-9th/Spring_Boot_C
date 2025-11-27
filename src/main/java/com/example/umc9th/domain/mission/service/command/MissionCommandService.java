package com.example.umc9th.domain.mission.service.command;

import com.example.umc9th.domain.mission.dto.request.MissionRequestDTO;
import com.example.umc9th.domain.mission.dto.response.MemberMissionResponseDTO;
import com.example.umc9th.domain.mission.dto.response.MissionResponseDTO;
import com.example.umc9th.domain.mission.dto.response.MyMissionResponseDTO;

public interface MissionCommandService {
    public MissionResponseDTO.createMission createMission(Long storeId, MissionRequestDTO.createMission missionRequestDTO);
    public MissionResponseDTO.memberMissionDTO challengeMission(Long memberId, Long missionId);
    public MemberMissionResponseDTO.MemberMissionPreViewDTO completeMission(Long missionId);
}
