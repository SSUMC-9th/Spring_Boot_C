package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.request.ReviewRequestDto;
import com.example.umc9th.domain.review.dto.response.ReviewResponseDto;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.service.command.ReviewCommandService;
import com.example.umc9th.domain.review.service.query.ReviewQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.umc9th.domain.review.dto.response.MyReviewResDto;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController implements ReviewControllerDocs {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/reviews/my")
    // 반환 타입을 ApiResponse<MyReviewResDto>로 변경
    public ApiResponse<MyReviewResDto> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Float score
    ) {
        MyReviewResDto responseDto = reviewQueryService.checkMyReview(memberId, storeName, score);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, responseDto);
    }

    // 리뷰 작성
    private final ReviewCommandService reviewCommandService;

    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResponseDto.createReview> createReview(@PathVariable Long storeId, @RequestBody @Valid ReviewRequestDto.createReview requestDTO) {

        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, reviewCommandService.createReview(storeId, requestDTO));
    }

    // 가게의 리뷰 목록 조회
    @GetMapping("/reviews")
    public ApiResponse<ReviewResponseDto.ReviewPreViewListDTO> getReviews(
            @RequestParam String storeName,
            @RequestParam(defaultValue = "1") Integer Page
    ){

        ReviewSuccessCode code = ReviewSuccessCode.REVIEW_FOUND;
        return ApiResponse.onSuccess(code, reviewQueryService.findReview(storeName, Page));
    }
}
