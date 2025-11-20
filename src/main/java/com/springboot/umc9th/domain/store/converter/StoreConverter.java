package com.springboot.umc9th.domain.store.converter;

import com.springboot.umc9th.domain.store.dto.req.StoreReqDTO;
import com.springboot.umc9th.domain.store.dto.res.CreateResDTO;
import com.springboot.umc9th.domain.store.entity.Local;
import com.springboot.umc9th.domain.store.entity.Store;

public class StoreConverter {

    public static Store toStore(StoreReqDTO.CreateDTO dto, Local local) {
        return Store.builder()
                .storeName(dto.storeName())
                .storeAddress(dto.storeAddress())
                .storeNumber(dto.storeNumber())
                .local(local)
                .build();
    }

    public static CreateResDTO.CreateStoreResDTO toCreateStoreResDTO(Store store) {
        return CreateResDTO.CreateStoreResDTO.builder()
                .storeId(store.getId())
                .localId(store.getLocal().getId())
                .storeName(store.getStoreName())
                .storeAddress(store.getStoreAddress())
                .build();
    }
}