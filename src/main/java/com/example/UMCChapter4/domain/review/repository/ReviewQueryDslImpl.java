package com.example.UMCChapter4.domain.review.repository;

import com.example.UMCChapter4.domain.member.entity.QMember;
import com.example.UMCChapter4.domain.review.entity.QReview;
import com.example.UMCChapter4.domain.review.entity.Review;
import com.example.UMCChapter4.domain.store.entity.QLocation;
import com.example.UMCChapter4.domain.store.entity.QStore;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl{

//    private final ReviewRepository reviewRepository;
    @PersistenceContext
    private final EntityManager em;

    @Bean
    public JPAQueryFactory jpaQueryFactory(){
        return new JPAQueryFactory(em);
    }

    @Override
    public List<Review> searchReview(
            Predicate predicate
    ){
        //JPA 세팅
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        //Q클래스
        QReview review = QReview.review;
        QStore store = QStore.store;
        QLocation location = QLocation.location;

        return queryFactory
                .selectFrom(review)
                .leftJoin(store).on(store.id.eq(review.store.id))
                .leftJoin(location).on(location.id.eq(store.location.id))
                .where(predicate)
                .fetch();
    }

    // Chapter 6. 내가 쓴 리뷰 보기(가게별, 별점 별)
    @Override
    public List<Review> searchMyReview(
            Predicate predicate
    ){
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        //Q클래스
        QReview review = QReview.review;
        QStore store = QStore.store;

        return queryFactory
                .selectFrom(review)
                .leftJoin(review.store, store).fetchJoin()
                .where(predicate)
                .fetch();
    }
}
