package com.example.UMCChapter4.domain.review.dto;

import lombok.Builder;

public class ReviewReqDTO {

    @Builder
    public record ReviewWriteDTO(
            Long storeId,
            Float rate,            // 1~5
            String description
    ) {}

}
