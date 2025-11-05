package com.springboot.umc9th.domain.review.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.umc9th.domain.review.entity.QReview;
import com.springboot.umc9th.domain.review.entity.Review;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryDslImpl  implements ReviewQueryDsl{
    private final ReviewRepository reviewRepository;
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
    public List<Review> searchMyReview(Predicate predicate) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QReview review = QReview.review;

        return queryFactory
                .selectFrom(review)
                .where(predicate)
                .orderBy(review.createdAt.desc())
                .fetch();
    }

}
