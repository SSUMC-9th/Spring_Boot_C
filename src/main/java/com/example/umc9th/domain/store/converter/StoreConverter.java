package com.example.umc9th.domain.store.converter;

import com.example.umc9th.domain.store.dto.request.StoreRequestDTO;
import com.example.umc9th.domain.store.dto.response.StoreResponseDTO;
import com.example.umc9th.domain.store.entity.Region;
import com.example.umc9th.domain.store.entity.Store;

public class StoreConverter {
    // Entity -> DTO
    public static StoreResponseDTO.JoinDTO toJoinDTO(Store store) {
        return StoreResponseDTO.JoinDTO.builder()
                .storeId(store.getId())
                .build();
    }

    // DTO -> Entity
    public static Store toStore(StoreRequestDTO.joinDTO joinDTO) {
        return Store.builder()
                .name(joinDTO.name())
                .address(joinDTO.address())
                .region(Region.builder().id(joinDTO.regionId()).build())
                .build();

    }
}
