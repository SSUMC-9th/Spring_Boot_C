package com.springboot.umc9th.domain.store.service;

import com.springboot.umc9th.domain.mission.entity.Mission;
import com.springboot.umc9th.domain.mission.repository.MissionRepository;
import com.springboot.umc9th.domain.store.converter.StoreConverter;
import com.springboot.umc9th.domain.mission.dto.req.MissionReqDTO;
import com.springboot.umc9th.domain.store.dto.req.StoreReqDTO;
import com.springboot.umc9th.domain.store.dto.res.CreateResDTO;
import com.springboot.umc9th.domain.mission.dto.res.MissionResDTO;
import com.springboot.umc9th.domain.store.entity.Local;
import com.springboot.umc9th.domain.store.entity.Store;
import com.springboot.umc9th.domain.store.exception.StoreException;
import com.springboot.umc9th.domain.store.exception.code.StoreErrorCode;
import com.springboot.umc9th.domain.store.repository.LocalRepository;
import com.springboot.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final LocalRepository localRepository;
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    @Transactional
    @Override
    public CreateResDTO.CreateStoreResDTO createStore(Long localId, StoreReqDTO.CreateDTO dto) {


        Local local = localRepository.findById(localId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.LOCAL_NOT_FOUND));

        // Store 생성
        Store store = StoreConverter.toStore(dto, local);
        storeRepository.save(store);

        return StoreConverter.toCreateStoreResDTO(store);
    }
}