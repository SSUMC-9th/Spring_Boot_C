package com.springboot.umc9th.domain.review.dto.res;

import lombok.Builder;

public class ReviewResDTO {

    @Builder
    public record CreateReviewResDTO(
            Long reviewId,
            String content,
            Integer rating
    ){}
}
