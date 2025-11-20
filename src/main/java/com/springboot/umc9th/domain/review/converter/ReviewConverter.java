package com.springboot.umc9th.domain.review.converter;


import com.springboot.umc9th.domain.review.entity.Review;
import com.springboot.umc9th.domain.review.dto.res.ReviewResDTO;

public class ReviewConverter {

    public static ReviewResDTO.CreateReviewResDTO toCreateReviewResDTO(Review review) {

        return ReviewResDTO.CreateReviewResDTO.builder()
                .reviewId(review.getId())
                .content(review.getReviewContent())
                .rating(review.getReviewScore())
                .build();
    }
}