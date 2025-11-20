package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.request.ReviewRequestDto;
import com.example.umc9th.domain.review.dto.response.ReviewResponseDto;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.command.ReviewCommandService;
import com.example.umc9th.domain.review.service.query.ReviewQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.umc9th.domain.review.dto.response.MyReviewResDto;



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

    // 리뷰 작성
    private final ReviewCommandService reviewCommandService;

    @PostMapping("/")
    public ApiResponse<ReviewResponseDto.createReview> createReview(
            @RequestBody @Valid ReviewRequestDto request
    ) {
        Long memberId = 1L; // 아직 DB에 아무 유저도 없기 때문에 임시로 값 작성(유저1)
        Review review = reviewCommandService.createReview(memberId, request);

        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, ReviewConverter.toCreateReview(review));
    }
}
