package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.member.dto.request.MemberRequestDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.review.dto.request.ReviewRequestDTO;
import com.example.umc9th.domain.review.dto.response.ReviewResponseDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.dto.request.StoreRequestDTO;
import com.example.umc9th.domain.store.dto.response.StoreResponseDTO;
import com.example.umc9th.domain.store.entity.Region;
import com.example.umc9th.domain.store.entity.Store;

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


    // Entity -> DTO
    public static ReviewResponseDTO.createReview toCreateDTO(Review review) {
        return ReviewResponseDTO.createReview.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    // DTO -> Entity
    public static Review toReview(Long storeId, ReviewRequestDTO.createReview dto) {
        return Review.builder()
                .content(dto.content())
                .star(dto.star())
                .store(Store.builder().id(storeId).build())
                .member(Member.builder().id(dto.memberId()).build())
                .build();
    }


}
