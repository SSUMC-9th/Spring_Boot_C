package com.example.umc9th.domain.review.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ReviewRequestDto {

    public record createReview(
            Long memberId,

            @NotBlank(message = "리뷰 내용은 공백일 수 없습니다.")
            String content,

            @NotNull(message = "별점은 필수입니다.")
            Float rating_score,

            String photoUrl
    ){}

}
