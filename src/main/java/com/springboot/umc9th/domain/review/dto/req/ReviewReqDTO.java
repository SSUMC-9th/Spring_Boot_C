package com.springboot.umc9th.domain.review.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

public class ReviewReqDTO {

    @Builder
    public record CreateReviewDTO(
            @NotBlank
            String content,

            @NotNull
            Integer rating
    ){}
}
