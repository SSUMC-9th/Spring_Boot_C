package com.example.umc9th.domain.review.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponseDTO {

    @Builder
    @Data
    public static class MyReview {
        private Long reviewId;
        private String content;
        private double star;
        private String storeName;  // 리뷰 대상 가게 이름
        private String name; // 리뷰 작성자 이름

        @QueryProjection
        public MyReview(Long reviewId, String content, double star, String storeName, String name) {
            this.reviewId = reviewId;
            this.content = content;
            this.star = star;
            this.storeName = storeName;
            this.name = name;
        }

    }

    @Builder
    public record createReview(
            Long reviewId,
            LocalDateTime createdAt
    ){}

    // 리뷰 정보들의 목록
    @Builder
    public record ReviewPreViewListDTO(
            List<ReviewPreViewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record ReviewPreViewDTO(
            String ownerNickname,
            Float score,
            String body,
            LocalDate createdAt
    ){}



}
