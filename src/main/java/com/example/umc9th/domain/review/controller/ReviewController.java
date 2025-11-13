package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.MyReviewDto;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.umc9th.domain.review.dto.MyReviewResDto;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/my")

    // 반환 타입을 ApiResponse<MyReviewResDto>로 변경
    public ApiResponse<MyReviewResDto> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Float score
    ) {
        MyReviewResDto responseDto = reviewQueryService.checkMyReview(memberId, storeName, score);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, responseDto);
    }
}
