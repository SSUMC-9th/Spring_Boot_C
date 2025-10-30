package com.example.UMCChapter4.domain.review.dto;

public record WriteReviewDto(
        Long memberId,
        Long storeId,
        float rate,            // 1~5
        String description
) {}
