package com.springboot.umc9th.domain.review.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.springboot.umc9th.domain.review.entity.QReview;
import com.springboot.umc9th.domain.review.entity.Review;
import com.springboot.umc9th.domain.review.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewQueryService {
    private final ReviewRepository reviewRepository;

    public ReviewQueryService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> searchReview(String query, String type) {
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();


        if (type.equals("location")) {
            builder.and(review.store.local.local.contains(query));
        }
        if (type.equals("star")) {
            builder.and(review.reviewScore.goe(Float.parseFloat(query)));
        }
        if(type.equals("both")) {

            String firstQuery = query.split("&")[0];
            String secondQuery = query.split("&")[1];

            builder.and(review.store.local.local.contains(firstQuery));
            builder.and(review.reviewScore.goe(Float.parseFloat(secondQuery)));

        }
        List<Review> reviewList = reviewRepository.searchReview(builder);
        return reviewList;
    }
    public List<Review> searchMyReviews(Long memberId, String storeName, Integer score) {
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        // 내가 쓴 리뷰만
        builder.and(review.member.id.eq(memberId));

        // 가게 이름 필터 (선택)
        if (storeName != null && !storeName.isEmpty()) {
            builder.and(review.store.local.local.contains(storeName));
        }

        // 별점 필터 (선택)
        if (score != null) {
            builder.and(review.reviewScore.eq(score));
        }

        Predicate predicate = builder;
        return reviewRepository.searchReview(predicate);
    }
}
