package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.review.dto.MyReviewDto;
import com.example.umc9th.domain.review.dto.request.ReviewRequestDto;
import com.example.umc9th.domain.review.dto.response.MyReviewResDto;
import com.example.umc9th.domain.review.dto.response.ReviewResponseDto;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.Store;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewConverter {

    // MyReviewDto를 MyReviewResDto로 변환
    public static MyReviewResDto toMyReviewResDto(List<MyReviewDto> reviewList) {
        return MyReviewResDto.builder()
                .reviewList(reviewList)
                .listSize(reviewList.size())
                .build();
    }

    // ReviewRequestDto를 Review 엔티티로 변환
    public static Review toReview(ReviewRequestDto request, Member member, Store store) {
        return Review.builder()
                .member(member)
                .store(store)
                .content(request.getContent())
                .ratingScore(request.getRating_score())
                .photoUrl(request.getImage_url())
                .build();
    }

    // Review 엔티티를 ReviewResponseDto(createReview)로 변환
    public static ReviewResponseDto.createReview toCreateReview(Review review) {
        return new ReviewResponseDto.createReview(
                review.getId(),
                review.getCreatedAt()
        );
    }
}