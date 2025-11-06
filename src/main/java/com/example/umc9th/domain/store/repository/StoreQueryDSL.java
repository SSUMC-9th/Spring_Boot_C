package com.example.umc9th.domain.store.repository;

import com.example.umc9th.domain.store.entity.Store;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface StoreQueryDSL {
    // 가게 이름 검색
    List<Store> searchStore(Predicate predicate);

}
