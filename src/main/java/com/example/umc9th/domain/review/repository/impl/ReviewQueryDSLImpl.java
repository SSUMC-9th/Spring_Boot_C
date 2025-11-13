package com.example.umc9th.domain.review.repository.impl;

import com.example.umc9th.domain.member.entity.QMember;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewQueryDSL;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.QRegion;
import com.example.umc9th.domain.store.entity.QStore;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryDSLImpl implements ReviewQueryDSL {

    private final EntityManager em;

    // 검색 API
    public List<Review> searchReview(Predicate predicate) {
        // JPA 세팅
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        // Q클래스 선언
        QReview review = QReview.review;
        QStore store = QStore.store;
        QRegion region = QRegion.region;

//        return queryFactory
//                .selectFrom(review).where(predicate).fetch();
        return queryFactory
                .selectFrom(review)
                .leftJoin(store).on(store.id.eq(review.store.id))
                .leftJoin(region).on(region.id.eq(store.region.id))
                .where(predicate)
                .fetch();
    }

    // 내가 작성한 리뷰 보기 API
    public List<Review> searchMyReview(Predicate predicate) {
        // JPA 세팅
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        // Q클래스 선언
        QReview review = QReview.review;
        QStore store = QStore.store;
        QMember member = QMember.member;


        return queryFactory
                .select(review)
                .from(review)
                // FETCH JOIN 적용
                .join(review.store, store)
                .join(review.member, member)
                .where(predicate)
                .fetch();

    }


}
