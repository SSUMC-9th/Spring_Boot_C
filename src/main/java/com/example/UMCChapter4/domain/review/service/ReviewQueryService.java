package com.example.UMCChapter4.domain.review.service;

import com.example.UMCChapter4.domain.member.entity.QMember;
import com.example.UMCChapter4.domain.review.entity.QReview;
import com.example.UMCChapter4.domain.review.entity.Review;
import com.example.UMCChapter4.domain.review.exception.ReviewException;
import com.example.UMCChapter4.domain.review.exception.code.ReviewErrorCode;
import com.example.UMCChapter4.domain.review.repository.ReviewQueryDsl;
import com.example.UMCChapter4.domain.review.repository.ReviewQueryDslImpl;
import com.example.UMCChapter4.domain.review.repository.ReviewRepository;
import com.example.UMCChapter4.domain.store.entity.QLocation;
import com.example.UMCChapter4.domain.store.entity.QStore;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {
    public final ReviewRepository reviewRepository;

    public List<Review> searchReview(
            String query,
            String type
    ) {
        //Q클래스 정의
        QReview review = QReview.review;
        QLocation location = QLocation.location;

        //BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        //BooleanBuilder 사용

        //동적 쿼리: 검색 조건
        if (type.equals("location")) {
            builder.and(review.store.location.name.contains(query));
        }
        if (type.equals("rate")) {
            Float rateParam = Float.parseFloat(query);
            Float epsilon = 0.0001f; // 허용 오차

            builder.and(
                    review.rate.goe(rateParam - epsilon)
                            .and(review.rate.loe(rateParam + epsilon))
            );
        }
        if (type.equals("both")) {

            // & 기준 변환
            String firstQuery = query.split("&")[0];
            String secondQuery = query.split("&")[1];

            // 동적 쿼리
            builder.and(review.store.location.name.contains(firstQuery));
            builder.and(review.rate.goe(Float.parseFloat(secondQuery)));
        }

        List<Review> reviewList = reviewRepository.searchReview(builder);

        // 임시 예외 처리
        if (reviewList.isEmpty()) {
            throw new ReviewException(ReviewErrorCode.REVIEW_EXCEPTION);
        }


        return reviewList;
    }

    //미션 가게별 별점별 리뷰
    public List<Review> searchMyReview(
            String query, // 가게별, 별점별
            String type,
            Long memberId
    ){
        QReview review = QReview.review;
        QStore store = QStore.store;

        BooleanBuilder builder = new BooleanBuilder().and(review.member.id.eq(memberId));

        if (type.equals("store")) {
            builder.and(review.store.name.contains(query));
        }

        if (type.equals("rate")) {
            Float rateParam = Float.parseFloat(query);
            Float epsilon = 0.0001f; // 허용 오차

            builder.and(
                    review.rate.goe(rateParam - epsilon)
                            .and(review.rate.loe(rateParam + epsilon))
            );
        }

        if (type.equals("both")) {

            // & 기준 변환
            String firstQuery = query.split("&")[0];
            String secondQuery = query.split("&")[1];

            // 동적 쿼리
            builder.and(review.store.name.contains(firstQuery));

            Float rateParam = Float.parseFloat(query);
            Float epsilon = 0.0001f; // 허용 오차

            builder.and(
                    review.rate.goe(rateParam - epsilon)
                            .and(review.rate.loe(rateParam + epsilon))
            );
        }

        List<Review> reviewList = reviewRepository.searchMyReview(builder);

        // 임시 예외 처리
        if (reviewList.isEmpty()) {
            throw new ReviewException(ReviewErrorCode.REVIEW_EXCEPTION);
        }

        return reviewList;
    }
}
