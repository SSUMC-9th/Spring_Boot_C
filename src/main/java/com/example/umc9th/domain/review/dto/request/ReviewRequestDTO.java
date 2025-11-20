package com.example.umc9th.domain.review.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

public class ReviewRequestDTO {

    public record createReview(
            @NotBlank(message = "내용은 필수입니다.")
            String content,
            @NotNull(message = "별점은 필수입니다.")
            Double star,
            Long memberId
    ){}
}
