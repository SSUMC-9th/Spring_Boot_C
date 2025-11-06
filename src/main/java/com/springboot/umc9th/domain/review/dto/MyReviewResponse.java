package com.springboot.umc9th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MyReviewResponse {
    private Long reviewId;
    private String storeName;
    private double rating;
    private String content;
    private LocalDateTime createdAt;
}
