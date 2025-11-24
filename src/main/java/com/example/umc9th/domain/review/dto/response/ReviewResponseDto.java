package com.example.umc9th.domain.review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ReviewResponseDto {

    @Builder
    public record createReview(
            Long reviewId,
            LocalDateTime createdAt
    ){}
}