package com.example.UMCChapter4.domain.mission.service.query;

import com.example.UMCChapter4.domain.mission.converter.MissionConverter;
import com.example.UMCChapter4.domain.mission.dto.mission.MissionResDTO;
import com.example.UMCChapter4.domain.mission.entity.Mission;
import com.example.UMCChapter4.domain.mission.repository.MissionRepository;
import com.example.UMCChapter4.domain.review.entity.Review;
import com.example.UMCChapter4.domain.store.entity.Store;
import com.example.UMCChapter4.domain.store.exception.StoreException;
import com.example.UMCChapter4.domain.store.exception.code.StoreErrorCode;
import com.example.UMCChapter4.domain.store.repository.StoreRepository;
import com.example.UMCChapter4.global.validator.PageValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    public MissionResDTO.MissionPreviewListDTO getMission(String storeName, Integer pageNumber) {
        Store store = storeRepository.findByName(storeName)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        int PAGE_SIZE = 10;
        PageRequest pageRequest = PageRequest.of(pageNumber, PAGE_SIZE);
        Page<Mission> result = missionRepository.findAllByStore(store, pageRequest);

        PageValidator.validatePageRequest(result); // pageNumber 검사

        return MissionConverter.toMissionPreviewListDTO(result);
    }
}
