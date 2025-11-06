package com.example.umc9th.domain.store.repository;

import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.store.entity.QRegion;
import com.example.umc9th.domain.store.entity.QStore;
import com.example.umc9th.domain.store.entity.Store;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreQueryDSLImpl implements StoreQueryDSL{
    private final EntityManager em;


    // 가게 이름 검색
    public List<Store> searchStore(Predicate predicate){
        // JPA 세팅
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        // Q클래스 선언
        QStore store = QStore.store;
        QRegion region = QRegion.region;

        return queryFactory
                .selectFrom(store)
                .leftJoin(region).on(region.id.eq(store.region.id))
                .where(predicate)
                .fetch();
    }
}
