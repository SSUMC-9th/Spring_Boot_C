package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.dto.MyReviewDto;
import com.example.umc9th.domain.review.entity.Review;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface ReviewQueryDsl {

    // 내 리뷰 조회 API
    List<MyReviewDto> checkMyReview(Long memberId, String storeName, Float score);
}
