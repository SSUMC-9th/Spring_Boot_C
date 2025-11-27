package com.springboot.umc9th.domain.mission.service;

import com.springboot.umc9th.domain.mission.dto.res.MissionResDTO;

public interface MissionQueryService {
    MissionResDTO.MissionPreViewListDTO getMissionList(Long storeId, Integer page);

    MissionResDTO.MyMissionPreViewListDTO getMyMissionList(Long memberId, Integer page);
}
