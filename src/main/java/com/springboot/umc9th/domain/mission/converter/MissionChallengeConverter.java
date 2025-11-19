package com.springboot.umc9th.domain.mission.converter;


import com.springboot.umc9th.domain.mission.dto.res.MissionChallengeResDTO;
import com.springboot.umc9th.domain.mission.entity.mapping.UserMission;

public class MissionChallengeConverter {

    public static MissionChallengeResDTO.ChallengeResDTO toChallengeResDTO(UserMission userMission){

        return MissionChallengeResDTO.ChallengeResDTO.builder()
                .memberMissionId(userMission.getId())
                .missionId(userMission.getMission().getId())
                .memberId(userMission.getMember().getId())
                .build();
    }
}
