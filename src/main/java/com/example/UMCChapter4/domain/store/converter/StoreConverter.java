package com.example.UMCChapter4.domain.store.converter;

import com.example.UMCChapter4.domain.member.dto.MemberReqDTO;
import com.example.UMCChapter4.domain.member.dto.MemberResDTO;
import com.example.UMCChapter4.domain.member.entity.Member;
import com.example.UMCChapter4.domain.member.enums.Address;
import com.example.UMCChapter4.domain.store.dto.StoreReqDTO;
import com.example.UMCChapter4.domain.store.dto.StoreResDTO;
import com.example.UMCChapter4.domain.store.entity.Location;
import com.example.UMCChapter4.domain.store.entity.Store;
import com.example.UMCChapter4.domain.store.exception.LocationException;
import com.example.UMCChapter4.domain.store.exception.code.LocationErrorCode;
import com.example.UMCChapter4.domain.store.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

public class StoreConverter {

    // Store -> StoreCreateDTO
    public static StoreResDTO.StoreCreateDTO toCreateResDTO(
            Store store
    ){
        return StoreResDTO.StoreCreateDTO.builder()
                .storeId(store.getId())
                .build();
    }

    // StoreCreateDTO -> Store
    public static Store toStore(
            StoreReqDTO.StoreCreateDTO dto,
            Location location
    ){
        return Store.builder()
                .name(dto.name())
                .managerNumber(dto.managerNumber())
                .address(dto.address()) // 지역번호 기반 ENUM
                .detailAddress(dto.detail_address())
                .reviewList(new ArrayList<>())
                .missionList(new ArrayList<>())
                .location(location)
                .build();
    }
}
