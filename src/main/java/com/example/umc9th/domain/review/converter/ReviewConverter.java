package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.review.dto.response.ReviewResponseDTO;
import com.example.umc9th.domain.review.entity.Review;

import java.util.stream.Collectors;
import java.util.List;

public class ReviewConverter {
    // 객체 -> DTO
    // 단일 객체용
    public static ReviewResponseDTO.MyReview toReviewDTO(Review review) {
        return ReviewResponseDTO.MyReview.builder()
                .reviewId(review.getId())
                .name(review.getMember().getName())
                .star(review.getStar())
                .content(review.getContent())
                .storeName(review.getStore().getName())
                .build();
    }

    // 리스트용
    public static List<ReviewResponseDTO.MyReview> toReviewDTOList(List<Review> reviews) {
        return reviews.stream()
                .map(ReviewConverter::toReviewDTO)
                .collect(Collectors.toList());
    }


    // 객체 -> DTO
//    public static ReviewResponseDTO.Exception toExceptionDTO(String testing){
//        return TestResponseDTO.Exception.builder()
//                .testString(testing)
//                .build();
//    }

}
