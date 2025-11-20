package com.example.UMCChapter4.domain.store.service;

import com.example.UMCChapter4.domain.member.enums.Address;
import com.example.UMCChapter4.domain.store.converter.StoreConverter;
import com.example.UMCChapter4.domain.store.dto.StoreReqDTO;
import com.example.UMCChapter4.domain.store.dto.StoreResDTO;
import com.example.UMCChapter4.domain.store.entity.Location;
import com.example.UMCChapter4.domain.store.entity.Store;
import com.example.UMCChapter4.domain.store.exception.LocationException;
import com.example.UMCChapter4.domain.store.exception.code.LocationErrorCode;
import com.example.UMCChapter4.domain.store.repository.LocationRepository;
import com.example.UMCChapter4.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {
    private final LocationRepository locationRepository;
    private final StoreRepository storeRepository;

    @Override
    public StoreResDTO.StoreCreateDTO createStore(StoreReqDTO.StoreCreateDTO ReqDTO) {

        Location location = locationRepository.findById(ReqDTO.locationId())
                .orElseThrow(() -> new LocationException(LocationErrorCode.NOT_FOUND));

        Store store = StoreConverter.toStore(ReqDTO, location);
        storeRepository.save(StoreConverter.toStore(ReqDTO, location));

        StoreResDTO.StoreCreateDTO ResDTO = StoreConverter.toCreateResDTO(store);

        return ResDTO;
    }
}
