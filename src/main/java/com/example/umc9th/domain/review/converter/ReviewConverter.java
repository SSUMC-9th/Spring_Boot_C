package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.review.dto.MyReviewDto;
import com.example.umc9th.domain.review.dto.MyReviewResDto;

import java.util.List;

public class ReviewConverter {

    // MyReviewDto를 MyReviewResDto로 변환
    public static MyReviewResDto toMyReviewResDto(List<MyReviewDto> reviewList) {
        return MyReviewResDto.builder()
                .reviewList(reviewList)
                .listSize(reviewList.size())
                .build();
    }
}