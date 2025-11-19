package com.example.UMCChapter4.domain.mission.service.command;

import com.example.UMCChapter4.domain.mission.converter.MissionConverter;
import com.example.UMCChapter4.domain.mission.dto.mission.MissionReqDTO;
import com.example.UMCChapter4.domain.mission.dto.mission.MissionResDTO;
import com.example.UMCChapter4.domain.mission.entity.Mission;
import com.example.UMCChapter4.domain.mission.repository.MissionRepository;
import com.example.UMCChapter4.domain.store.entity.Store;
import com.example.UMCChapter4.domain.store.exception.StoreException;
import com.example.UMCChapter4.domain.store.exception.code.StoreErrorCode;
import com.example.UMCChapter4.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public MissionResDTO.MissionCreateDTO createMission(MissionReqDTO.MissionCreateDTO ReqDTO) {

        Store store = storeRepository.findById(ReqDTO.storeId())
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        Mission mission = MissionConverter.toMission(ReqDTO, store);

        missionRepository.save(mission);
        store.getMissionList().add(mission);

        return MissionConverter.toMissionCreateDTO(mission);
    }
}
