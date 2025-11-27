package com.example.UMCChapter4.domain.mission.service.command;

import com.example.UMCChapter4.domain.mission.dto.membermission.MemberMissionReqDTO;
import com.example.UMCChapter4.domain.mission.dto.membermission.MemberMissionResDTO;

public interface MemberMissionCommandService {
    MemberMissionResDTO.MemberMissionInProgressDTO createMemberMission(MemberMissionReqDTO.MemberMissionCreateDTO ReqDTO);
    MemberMissionResDTO.MemberMissionCompleteDTO completeMemberMission(MemberMissionReqDTO.MemberMissionCompleteDTO ReqDTO);
}
