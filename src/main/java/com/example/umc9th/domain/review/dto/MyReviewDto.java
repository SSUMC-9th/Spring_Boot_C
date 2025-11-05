package com.example.umc9th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class MyReviewDto {
    private Long reviewId;
    private String storeName;  // 가게 이름
    private String content;    // 리뷰 내용
    private Float ratingScore;   // 별점
    private LocalDate createdAt; // 작성일

}
