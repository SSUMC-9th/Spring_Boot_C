package com.example.UMCChapter4.domain.mission.service.query;

import com.example.UMCChapter4.domain.mission.dto.membermission.MemberMissionResDTO;

public interface MemberMissionQueryService {
    MemberMissionResDTO.MemberMissionStatusListDTO getStatusMemberMission(String status, String memberName, Integer pageNumber);

}
