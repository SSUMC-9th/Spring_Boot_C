package com.example.umc9th.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class MyReviewResDto {

    private List<MyReviewDto> reviewList;
    private Integer listSize;
}
