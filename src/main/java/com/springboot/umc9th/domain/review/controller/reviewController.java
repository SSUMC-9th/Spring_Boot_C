package com.springboot.umc9th.domain.review.controller;

import com.springboot.umc9th.domain.review.dto.MyReviewResponse;
import com.springboot.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.springboot.umc9th.domain.review.dto.res.ReviewResDTO;
import com.springboot.umc9th.domain.review.entity.Review;
import com.springboot.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.springboot.umc9th.domain.review.service.ReviewCommandService;
import com.springboot.umc9th.domain.review.service.ReviewQueryService;
import com.springboot.umc9th.global.apiPayload.ApiResponse;
import com.springboot.umc9th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/reviews")
@Tag(name = "리뷰 API", description = "리뷰 생성 및 관리 관련 기능")
public class reviewController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/search")
    @Operation(summary = "리뷰 조회", description = "리뷰 전체 조회")
    public ApiResponse<List<Review>> searchReview(
            @RequestParam String query,
            @RequestParam Integer page,
            @RequestParam String type
    ) {
        List<Review> result = reviewQueryService.searchReview(query, type);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    @GetMapping("/my")
    @Operation(summary = "내 리뷰 조회", description = "내가 작성한 리뷰 전체 조회")
    public ApiResponse<List<MyReviewResponse>> searchMyReview(
            @RequestParam Long memberId,
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String type
    ) {
        List<MyReviewResponse> result = reviewQueryService.searchMyReviews(memberId, query, type);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
    private final ReviewCommandService reviewCommandService;

    @PostMapping("/stores/{storeId}")
    @Operation(summary = "리뷰 생성", description = "가게에 리뷰 추가하기 API")
    public ApiResponse<ReviewResDTO.CreateReviewResDTO> createReview(
            @PathVariable Long storeId,
            @RequestBody @Valid ReviewReqDTO.CreateReviewDTO dto
    ){
        return ApiResponse.onSuccess(
                ReviewSuccessCode.CREATED,
                reviewCommandService.createReview(storeId, dto)
        );
    }
}
