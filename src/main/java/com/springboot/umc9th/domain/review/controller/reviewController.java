package com.springboot.umc9th.domain.review.controller;

import com.springboot.umc9th.domain.review.dto.MyReviewResponse;
import com.springboot.umc9th.domain.review.entity.Review;
import com.springboot.umc9th.domain.review.service.ReviewQueryService;
import com.springboot.umc9th.global.apiPayload.ApiResponse;
import com.springboot.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/reviews")
public class reviewController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/search")
    public ApiResponse<List<Review>> searchReview(
            @RequestParam String query,
            @RequestParam Integer page,
            @RequestParam String type
    ) {
        List<Review> result = reviewQueryService.searchReview(query, type);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    @GetMapping("/my")
    public ApiResponse<List<MyReviewResponse>> searchMyReview(
            @RequestParam Long memberId,
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String type
    ) {
        List<MyReviewResponse> result = reviewQueryService.searchMyReviews(memberId, query, type);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
