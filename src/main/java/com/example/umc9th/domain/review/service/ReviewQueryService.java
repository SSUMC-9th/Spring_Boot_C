package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.MyReviewDto;
import com.example.umc9th.domain.review.dto.MyReviewResDto;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    // 반환 타입을 MyReviewResDto로 변경
    public MyReviewResDto checkMyReview(Long memberId, String storeName, Float score) {

        // Repository에서 DTO 리스트 조회
        List<MyReviewDto> myReviews = reviewRepository.checkMyReview(memberId, storeName, score);

        // Converter를 사용하여 Wrapper DTO로 변환 후 반환
        return ReviewConverter.toMyReviewResDto(myReviews);
    }

}