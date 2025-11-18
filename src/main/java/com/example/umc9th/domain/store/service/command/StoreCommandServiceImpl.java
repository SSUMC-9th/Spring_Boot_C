package com.example.umc9th.domain.store.service.command;

import com.example.umc9th.domain.store.converter.StoreConverter;
import com.example.umc9th.domain.store.dto.request.StoreRequestDTO;
import com.example.umc9th.domain.store.dto.response.StoreResponseDTO;
import com.example.umc9th.domain.store.entity.Region;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.RegionRepository;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {
    private final StoreRepository storeRepository;
    // 특정 지역 가게 추가
    @Override
    @Transactional
    public StoreResponseDTO.JoinDTO createStore(StoreRequestDTO.joinDTO requestDTO) {
        Store store = Store.builder()
                .name(requestDTO.name())
                .address(requestDTO.address())
                .region(Region.builder().id(requestDTO.regionId()).build())
                .build();

        storeRepository.save(store);
        return StoreConverter.toJoinDTO(store);
    }
}
