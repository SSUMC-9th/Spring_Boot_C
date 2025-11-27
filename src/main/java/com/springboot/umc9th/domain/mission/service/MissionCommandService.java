package com.springboot.umc9th.domain.mission.service;

import com.springboot.umc9th.domain.mission.dto.req.MissionReqDTO;
import com.springboot.umc9th.domain.mission.dto.res.MissionChallengeResDTO;
import com.springboot.umc9th.domain.mission.dto.res.MissionResDTO;
import org.springframework.transaction.annotation.Transactional;

public interface MissionCommandService {
    @Transactional
    MissionChallengeResDTO.ChallengeResDTO challengeMission(Long missionId);

    @Transactional
    MissionResDTO.CreateMissionResDTO createMission(Long storeId, MissionReqDTO.CreateDTO dto);

    MissionResDTO.MyMissionDTO completeMission(Long memberId, Long missionId);}
