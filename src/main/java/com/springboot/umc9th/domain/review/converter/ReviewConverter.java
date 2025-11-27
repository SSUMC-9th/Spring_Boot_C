package com.springboot.umc9th.domain.review.converter;


import com.springboot.umc9th.domain.review.entity.Review;
import com.springboot.umc9th.domain.review.dto.res.ReviewResDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewResDTO.CreateReviewResDTO toCreateReviewResDTO(Review review) {

        return ReviewResDTO.CreateReviewResDTO.builder()
                .reviewId(review.getId())
                .content(review.getReviewContent())
                .rating(review.getReviewScore())
                .build();
    }

    // result -> DTO
    public static ReviewResDTO.ReviewPreViewListDTO toReviewPreviewListDTO(
            Page<Review> result
    ){
        return ReviewResDTO.ReviewPreViewListDTO.builder()
                .reviewList(result.getContent().stream()
                        .map(ReviewConverter::toReviewPreviewDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static ReviewResDTO.ReviewPreViewDTO toReviewPreviewDTO(
            Review review
    ){
        return ReviewResDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getReviewScore())
                .body(review.getReviewContent())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }
    public static ReviewResDTO.MyReviewPreViewListDTO toMyReviewPreViewListDTO(
            Page<Review> result
    ){
        // stream()을 사용하여 Entity 리스트를 DTO 리스트로 변환
        List<ReviewResDTO.MyReviewDTO> myReviewDTOList = result.getContent().stream()
                .map(ReviewConverter::toMyReviewDTO)
                .collect(Collectors.toList());

        return ReviewResDTO.MyReviewPreViewListDTO.builder()
                .reviewList(myReviewDTOList)
                .listSize(result.getContent().size())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }
    public static ReviewResDTO.MyReviewDTO toMyReviewDTO(
            Review review
    ){

        return ReviewResDTO.MyReviewDTO.builder()
                .storeName(review.getStore().getStoreName())
                .score(review.getReviewScore())
                .body(review.getReviewContent())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }

}