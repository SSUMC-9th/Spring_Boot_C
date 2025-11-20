package com.example.umc9th.domain.review.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

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




}
