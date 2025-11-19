package com.example.UMCChapter4.domain.mission.service.command;

import com.example.UMCChapter4.domain.mission.dto.mission.MissionReqDTO;
import com.example.UMCChapter4.domain.mission.dto.mission.MissionResDTO;

public interface MissionCommandService {
    MissionResDTO.MissionCreateDTO createMission(MissionReqDTO.MissionCreateDTO reqDTO);
}
