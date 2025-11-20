package com.example.UMCChapter4.domain.store.service;

import com.example.UMCChapter4.domain.store.dto.StoreReqDTO;
import com.example.UMCChapter4.domain.store.dto.StoreResDTO;

public interface StoreCommandService {
    StoreResDTO.StoreCreateDTO createStore(StoreReqDTO.StoreCreateDTO reqDTO);
}
