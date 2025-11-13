package com.example.UMCChapter4.domain.review.controller;

import com.example.UMCChapter4.domain.review.dto.ReviewResDTO;
import com.example.UMCChapter4.domain.review.entity.QReview;
import com.example.UMCChapter4.domain.review.entity.Review;
import com.example.UMCChapter4.domain.review.service.ReviewQueryService;
import com.example.UMCChapter4.global.apiPayload.ApiResponse;
import com.example.UMCChapter4.global.apiPayload.code.GeneralSuccessCode;
import com.example.UMCChapter4.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    public final ReviewQueryService reviewQueryService;

    @GetMapping("/search")
    public ApiResponse<List<ReviewResDTO.Searching>> searchReview(
            @RequestParam String query,
            @RequestParam String type
    ) {
        // 검색 및 예외 처리
        List<Review> reviewList = reviewQueryService.searchReview(query, type);

        // 응답 코드 처리
        GeneralSuccessCode code = GeneralSuccessCode.OK;

        // Convert
        List<ReviewResDTO.Searching> result = new ArrayList<>();
        for (Review review : reviewList) {
            result.add(ReviewResDTO.Searching.builder()
                    .searchDescription(review.getDescription())
                    .searchRate(review.getRate())
//                    .searchReviewPhotoList(review.getReviewPhotoList())
//                    .searchReviewReplyList(review.getReviewReplyList())
                    .build());
        }

        return ApiResponse.onSuccess(
                code,
                result
        );
    }

    @GetMapping("/member/search")
    public ApiResponse<List<ReviewResDTO.Searching>> searchMemberReview(
            @RequestParam String query,
            @RequestParam String type,
            @RequestParam Long memberId
    ) {
        // 검색 및 예외 처리
        List<Review> reviewList = reviewQueryService.searchMyReview(query, type, memberId);

        // 응답 코드 처리
        GeneralSuccessCode code = GeneralSuccessCode.OK;

        // Convert
        List<ReviewResDTO.Searching> result = new ArrayList<>();
        for (Review review : reviewList) {
            result.add(ReviewResDTO.Searching.builder()
                    .searchDescription(review.getDescription())
                    .searchRate(review.getRate())
                    .build());
        }

        return ApiResponse.onSuccess(
                code,
                result
        );
    }
}

