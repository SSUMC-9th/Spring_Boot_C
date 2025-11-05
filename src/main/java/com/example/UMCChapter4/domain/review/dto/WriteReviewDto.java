package com.example.UMCChapter4.domain.review.dto;

public record WriteReviewDto(
        Long memberId,
        Long storeId,
        Float rate,            // 1~5
        String description
) {}
