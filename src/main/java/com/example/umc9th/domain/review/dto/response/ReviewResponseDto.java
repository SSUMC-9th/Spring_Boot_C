package com.example.umc9th.domain.review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponseDto {

    @Builder
    public record createReview(
            Long reviewId,
            LocalDateTime createdAt
    ){}

    // 가게 리뷰 조회 목록 DTO
    @Builder
    public record ReviewPreViewListDTO(
            List<ReviewPreViewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    // 가게 리뷰 조회 DTO
    @Builder
    public record ReviewPreViewDTO(
            String ownerNickname,
            Float score,
            String body,
            LocalDate createdAt
    ){}
}