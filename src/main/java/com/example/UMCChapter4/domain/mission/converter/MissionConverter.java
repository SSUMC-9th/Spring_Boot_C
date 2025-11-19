package com.example.UMCChapter4.domain.mission.converter;

import com.example.UMCChapter4.domain.mission.dto.mission.MissionReqDTO;
import com.example.UMCChapter4.domain.mission.dto.mission.MissionResDTO;
import com.example.UMCChapter4.domain.mission.entity.Mission;
import com.example.UMCChapter4.domain.store.entity.Store;

import java.util.ArrayList;

public class MissionConverter {

    public static Mission toMission(MissionReqDTO.MissionCreateDTO ReqDTO, Store store){
        return Mission.builder()
                .deadline(ReqDTO.deadline())
                .condition(ReqDTO.condition())
                .points(ReqDTO.points())
                .memberMissionList(new ArrayList<>())
                .store(store)
                .build();
    }

    public static MissionResDTO.MissionCreateDTO toMissionCreateDTO(Mission mission){
        return MissionResDTO.MissionCreateDTO.builder()
                .missionId(mission.getId())
                .build();
    }
}
