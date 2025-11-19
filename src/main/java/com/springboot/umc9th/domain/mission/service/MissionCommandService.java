package com.springboot.umc9th.domain.mission.service;

import com.springboot.umc9th.domain.mission.dto.res.MissionChallengeResDTO;
import org.springframework.transaction.annotation.Transactional;

public interface MissionCommandService {
    @Transactional
    MissionChallengeResDTO.ChallengeResDTO challengeMission(Long missionId);
}
