package com.example.UMCChapter4.domain.mission.service.query;

import com.example.UMCChapter4.domain.mission.dto.mission.MissionResDTO;

public interface MissionQueryService {

    MissionResDTO.MissionPreviewListDTO getMission(String storeName, Integer pageNumber);
}
