package com.example.umc9th.domain.review.dto.response;

import com.example.umc9th.domain.review.dto.MyReviewDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class MyReviewResDto {

    private List<MyReviewDto> reviewList;
    private Integer listSize;
}
