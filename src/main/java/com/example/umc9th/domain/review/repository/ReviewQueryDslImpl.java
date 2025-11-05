package com.example.umc9th.domain.review.repository;


import com.example.umc9th.domain.review.dto.MyReviewDto;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;

// 아래 BooleanExpression 메서드에도 사용되기 때문에 static으로 선언
import static com.example.umc9th.domain.review.entity.QReview.review;
import static com.example.umc9th.domain.store.entity.QStore.store;
import static com.example.umc9th.domain.member.entity.QMember.member;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl{

    private final ReviewRepository reviewRepository;
    private final EntityManager em;


    @Override
    public List<MyReviewDto> checkMyReview(Long memberId, String storeName, Float score) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        return queryFactory
                .from(review)
                .join(review.member, member)
                .join(review.store, store)
                .select(Projections.constructor(MyReviewDto.class,
                        review.id,
                        store.name,
                        review.content,
                        review.ratingScore,
                        Expressions.dateTemplate(LocalDate.class, "DATE({0})", review.createdAt))   // 생성 일시(초단위)를 날짜로 변경
                )
                .where(
                        member.id.eq(memberId),
                        storeNameEq(storeName),
                        scoreEq(score)
                )
                .orderBy(review.createdAt.desc())
                .fetch();
    }

    private BooleanExpression storeNameEq(String storeName) {
        // StringUtils.hasText(): null 뿐만아니라 " "와 같은 공백 문자도 처리 가능
        return StringUtils.hasText(storeName) ? store.name.eq(storeName) : null;
    }

    // 별점 BooleanExpression (5점, 4점대, 3점대, ...)
    private BooleanExpression scoreEq(Float score) {
        if (score == null) {
            return null;
        }
        if (score == 5) {
            return review.ratingScore.eq(5.0f);
        }
        if (score >= 1 && score <= 4) {
            float minScore = score;
            float maxScore = minScore + 1.0f;
            return review.ratingScore.goe(minScore).and(review.ratingScore.lt(maxScore));
        }
        return null;
    }
}
