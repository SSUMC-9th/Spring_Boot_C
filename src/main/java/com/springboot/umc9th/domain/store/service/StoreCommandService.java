package com.springboot.umc9th.domain.store.service;

import com.springboot.umc9th.domain.mission.dto.req.MissionReqDTO;
import com.springboot.umc9th.domain.store.dto.req.StoreReqDTO;
import com.springboot.umc9th.domain.store.dto.res.CreateResDTO;
import com.springboot.umc9th.domain.mission.dto.res.MissionResDTO;
import org.springframework.transaction.annotation.Transactional;

public interface StoreCommandService {

    @Transactional
    CreateResDTO.CreateStoreResDTO createStore(Long localId, StoreReqDTO.CreateDTO dto);

}
