package com.springboot.umc9th.domain.review.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.springboot.umc9th.domain.review.dto.MyReviewResponse;
import com.springboot.umc9th.domain.review.entity.QReview;
import com.springboot.umc9th.domain.review.entity.Review;
import com.springboot.umc9th.domain.review.repository.ReviewQueryDsl;
import com.springboot.umc9th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewQueryService {
    private final ReviewRepository reviewRepository;
//    private final ReviewQueryDsl reviewQueryDsl;

    public ReviewQueryService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
//        this.reviewQueryDsl = reviewQueryDsl;
    }

    public List<Review> searchReview(String query, String type) {
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();


        if (type.equals("location")) {
            builder.and(review.store.local.name.contains(query));
        }
        if (type.equals("star")) {
            builder.and(review.reviewScore.goe(Float.parseFloat(query)));
        }
        if(type.equals("both")) {

            String firstQuery = query.split("&")[0];
            String secondQuery = query.split("&")[1];

            builder.and(review.store.local.name.contains(firstQuery));
            builder.and(review.reviewScore.goe(Float.parseFloat(secondQuery)));

        }
        List<Review> reviewList = reviewRepository.searchReview(builder);
        return reviewList;
    }
    public List<MyReviewResponse> searchMyReviews(Long memberId, String query, String type) {
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        // 필수 조건: 내가 쓴 리뷰만
        builder.and(review.member.id.eq(memberId));

        if (type.equals("storeName")) {
            builder.and(review.store.local.name.contains(query));
        }
        else if (type.equals("score")) {
            builder.and(review.reviewScore.eq(Integer.parseInt(query)));
        }
        else if(type.equals("both")) {
            String storeNameQuery = query.split("&")[0];
            String scoreQuery = query.split("&")[1];

            builder.and(review.store.local.name.contains(storeNameQuery));
            builder.and(review.reviewScore.eq(Integer.parseInt(scoreQuery)));
        }

        Predicate predicate = builder;
        return reviewRepository.searchMyReview(predicate);
    }
}
