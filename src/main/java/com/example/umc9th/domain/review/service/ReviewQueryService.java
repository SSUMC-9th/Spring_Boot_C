package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.MyReviewDto;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public List<MyReviewDto> checkMyReview(Long memberId, String storeName, Float score) {

        List<MyReviewDto> myReviews = reviewRepository.checkMyReview(memberId, storeName, score);

        return myReviews;
    }

}
