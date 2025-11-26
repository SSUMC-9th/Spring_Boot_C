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
                .listSize(result.getContent().size()) // 현재 페이지에 나온 개수
                .totalPage(result.getTotalPages())    // 전체 페이지 수
                .totalElements(result.getTotalElements()) // 전체 아이템 수
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }
    public static ReviewResDTO.MyReviewDTO toMyReviewDTO(
            Review review
    ){

        return ReviewResDTO.MyReviewDTO.builder()
                .storeName(review.getStore().getStoreName()) // Store 엔티티의 이름 가져오기
                .score(review.getReviewScore())                           // 변환된 점수
                .body(review.getReviewContent())        // [중요] 엔티티 필드명(reviewContent) 반영
                .createdAt(LocalDate.from(review.getCreatedAt())) // BaseEntity 시간
                .build();
    }

}