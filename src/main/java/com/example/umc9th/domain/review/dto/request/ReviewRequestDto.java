package com.example.umc9th.domain.review.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ReviewRequestDto {

    // member_id는 token에서 가져옴
    @NotNull(message = "가게 ID는 필수입니다.")
    private Long StoreId;

    @NotNull(message = "별점은 필수입니다.")
    private Float rating_score;

    @NotBlank(message = "리뷰 내용은 공백일 수 없습니다.")
    private String content;

    private String image_url;
}
