package com.example.UMCChapter4.domain.review.controller;

import com.example.UMCChapter4.domain.review.dto.ReviewReqDTO;
import com.example.UMCChapter4.domain.review.dto.ReviewResDTO;
import com.example.UMCChapter4.domain.review.entity.Review;
import com.example.UMCChapter4.domain.review.exception.code.ReviewSuccessCode;
import com.example.UMCChapter4.domain.review.service.command.ReviewCommandService;
import com.example.UMCChapter4.domain.review.service.query.ReviewQueryService;
import com.example.UMCChapter4.global.apiPayload.ApiResponse;
import com.example.UMCChapter4.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {
    public final ReviewQueryService reviewQueryService;
    public final ReviewCommandService reviewCommandService;

    @GetMapping("/search")
    public ApiResponse<List<ReviewResDTO.ReviewSearchDTO>> searchReview(
            @RequestParam String query,
            @RequestParam String type
    ) {
        // 검색 및 예외 처리
        List<Review> reviewList = reviewQueryService.searchReview(query, type);

        // Convert
        List<ReviewResDTO.ReviewSearchDTO> result = new ArrayList<>();
        for (Review review : reviewList) {
            result.add(ReviewResDTO.ReviewSearchDTO.builder()
                    .searchDescription(review.getDescription())
                    .searchRate(review.getRate())
//                    .searchReviewPhotoList(review.getReviewPhotoList())
//                    .searchReviewReplyList(review.getReviewReplyList())
                    .build());
        }

        return ApiResponse.onSuccess(
                ReviewSuccessCode.FOUND,
                result
        );
    }

    @GetMapping("/my/search")
    public ApiResponse<List<ReviewResDTO.ReviewSearchDTO>> searchMemberReview(
            @RequestParam String query,
            @RequestParam String type,
            @RequestParam Long memberId
    ) {
        // 검색 및 예외 처리
        List<Review> reviewList = reviewQueryService.searchMyReview(query, type, memberId);

        // Convert
        List<ReviewResDTO.ReviewSearchDTO> result = new ArrayList<>();
        for (Review review : reviewList) {
            result.add(ReviewResDTO.ReviewSearchDTO.builder()
                    .searchDescription(review.getDescription())
                    .searchRate(review.getRate())
                    .build());
        }

        return ApiResponse.onSuccess(
                ReviewSuccessCode.FOUND,
                result
        );
    }

    @PostMapping("/write")
    public ApiResponse<ReviewResDTO.ReviewWriteDTO> writeReview(
            @RequestBody ReviewReqDTO.ReviewWriteDTO ReqDTO // storeId, description, rate
    ){

        ReviewResDTO.ReviewWriteDTO ResDTO = reviewCommandService.writeReview(ReqDTO);

        return ApiResponse.onSuccess(
                ReviewSuccessCode.CREATED, // review 생성
                ResDTO
        );
    }
}

