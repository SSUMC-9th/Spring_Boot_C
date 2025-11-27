package com.springboot.umc9th.domain.review.dto.res;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class ReviewResDTO {

    @Builder
    public record CreateReviewResDTO(
            Long reviewId,
            String content,
            Integer rating
    ){}

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
            Integer score,
            String body,
            LocalDate createdAt
    ){}

    @Builder
    public record MyReviewPreViewListDTO(
            List<MyReviewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record MyReviewDTO(
            String storeName,
            Integer score,
            String body,
            LocalDate createdAt
    ){}
}
