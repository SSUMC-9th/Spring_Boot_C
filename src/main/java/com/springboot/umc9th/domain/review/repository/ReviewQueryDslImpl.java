package com.springboot.umc9th.domain.review.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.umc9th.domain.review.dto.MyReviewResponse;
import com.springboot.umc9th.domain.review.entity.QReview;
import com.springboot.umc9th.domain.review.entity.Review;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl  implements ReviewQueryDsl{
    private final EntityManager em;

    @Override
    public List<Review> searchReview(
            Predicate predicate
    ) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QReview review = QReview.review;

        return queryFactory
                .selectFrom(review)
                .where(predicate)
                .fetch();
    }
    @Override
    public List<MyReviewResponse> searchMyReview(Predicate predicate) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QReview review = QReview.review;

        return queryFactory
                .select(Projections.constructor(MyReviewResponse.class,
                        review.id,
                        review.store.storeName,
                        review.reviewScore.doubleValue().as("rating"),
                        review.reviewContent,
                        review.createdAt
                ))
                .from(review)
                .where(predicate)
                .orderBy(review.createdAt.desc())
                .fetch();
    }

}
