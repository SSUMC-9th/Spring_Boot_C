package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.mission.dto.response.MissionResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface MissionQueryService {

    MissionResponseDto.MissionPreViewListDTO getMissionListByStore(
            Long storeId,
            Integer page
    );

    MissionResponseDto.MissionPreViewListDTO getMyMissionList(Long memberId, Integer page);
}
