package com.example.umc9th.domain.store.service;

import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.store.entity.QRegion;
import com.example.umc9th.domain.store.entity.QStore;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreQueryService {
    private final StoreRepository storeRepository;

    // 1. 가게 이름 검색
    public List<Store> searchStore(String storeNameQuery){
        QStore store = QStore.store;
        QRegion region = QRegion.region;

        BooleanBuilder builder = new BooleanBuilder(store.region.id.eq(region.id));

        // 동적 쿼리: 검색 조건
        // 1. 가게 이름 필터링
        if (storeNameQuery != null && !storeNameQuery.isBlank()) {
            builder.and(storeNameDynamicSearch(storeNameQuery, store)); // 동적 검색 조건 함수 호출
        }

        // Repository 사용 & 결과 매핑
        return storeRepository.searchStore(builder);
    }

    // 가게 이름 공백 계산
    public BooleanBuilder storeNameDynamicSearch(String storeNameQuery, QStore store) {
        // 1. 공백 포함 검색어 (예: "민트 초코") -> OR 연산 (합집합)
        if (storeNameQuery.contains(" ")) {
            String[] words = storeNameQuery.trim().split("\\s+"); // 공백 기준으로 단어 분리

            // BooleanBuilder를 OR 조건으로 조립
            BooleanBuilder builder = new BooleanBuilder();
            for (String word : words) {
                // 각 단어가 포함된 가게를 OR로 연결 (민트 포함 OR 초코 포함)
                builder.or(store.name.contains(word));
            }
            return builder; // 최종 OR 조건을 반환


        }
        // 2. 공백 없는 검색어 (예: "민트초코") -> AND 연산 (단일 LIKE)
        else {
            // 검색어 전체가 포함된 가게만 조회
            // BooleanBuilder를 OR 조건으로 조립
            BooleanBuilder builder = new BooleanBuilder();
            // WHERE region_name LIKE '%query%
            return builder.and(store.name.contains(storeNameQuery));
        }

    }
}
