package com.example.UMCChapter4.domain.review.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class ReviewResDTO {

    @Builder
    public record ReviewSearchDTO(
            Long reviewId
    ){}

    @Builder
    public record ReviewSearchMyDTO(
            Long reviewId
    ){}


    @Builder
    public record ReviewWriteDTO(
            Long reviewId
    ){}

    @Builder
    public record ReviewPreviewListDTO(
            List<ReviewPreviewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record ReviewPreviewDTO(
            String nickname,
            String rate,
            String description,
            LocalDate createdAt
    ){}

    @Builder
    public record ReviewMyPreviewListDTO(
            List<ReviewMyPreviewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record ReviewMyPreviewDTO(
            String storeName,
            String nickname,
            String rate,
            String description,
            LocalDate createdAt
    ){}


}

