package com.example.UMCChapter4.domain.review.controller;

import com.example.UMCChapter4.domain.review.dto.ReviewReqDTO;
import com.example.UMCChapter4.domain.review.dto.ReviewResDTO;
import com.example.UMCChapter4.domain.review.exception.code.ReviewSuccessCode;
import com.example.UMCChapter4.domain.review.service.command.ReviewCommandService;
import com.example.UMCChapter4.domain.review.service.query.ReviewQueryService;
import com.example.UMCChapter4.global.annotation.ValidPage;
import com.example.UMCChapter4.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "리뷰 API")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController implements ReviewControllerDocs {
    public final ReviewQueryService reviewQueryService;
    public final ReviewCommandService reviewCommandService;

    @GetMapping("/search")
    public ApiResponse<List<ReviewResDTO.ReviewSearchDTO>> searchReview(
            @RequestParam String query,
            @RequestParam String type
    ) {
        // 검색 및 예외 처리
        List<ReviewResDTO.ReviewSearchDTO> ResDTO = reviewQueryService.searchReview(query, type);

        return ApiResponse.onSuccess(
                ReviewSuccessCode.FOUND,
                ResDTO
        );
    }

    @GetMapping("/my/search")
    public ApiResponse<List<ReviewResDTO.ReviewSearchMyDTO>> searchMemberReview(
            @RequestParam String query,
            @RequestParam String type,
            @RequestParam Long memberId
    ) {
        // 검색 및 예외 처리
        List<ReviewResDTO.ReviewSearchMyDTO> ResDTO = reviewQueryService.searchMyReview(query, type, memberId);

        return ApiResponse.onSuccess(
                ReviewSuccessCode.FOUND,
                ResDTO
        );
    }

    @PostMapping("/write")
    public ApiResponse<ReviewResDTO.ReviewWriteDTO> writeReview(
            @RequestBody ReviewReqDTO.ReviewWriteDTO ReqDTO // storeName, description, rate
    ){

        ReviewResDTO.ReviewWriteDTO ResDTO = reviewCommandService.writeReview(ReqDTO);

        return ApiResponse.onSuccess(
                ReviewSuccessCode.CREATED, // review 생성
                ResDTO
        );
    }

    // 가게의 리뷰 목록 조회
    @GetMapping("/store/get")
    public ApiResponse<ReviewResDTO.ReviewPreviewListDTO> getReviews(
            @RequestParam String storeName,
            @RequestParam(defaultValue = "1") @ValidPage Integer pageNumber
    ){

        return ApiResponse.onSuccess(
                ReviewSuccessCode.FOUND,
                reviewQueryService.getReviews(storeName, pageNumber-1)
        );
    }

    // 내가 작성한 리뷰 목록 조회
    @GetMapping("/member/get")
    public ApiResponse<ReviewResDTO.ReviewMyPreviewListDTO> getMyReviews(
        @RequestParam String memberName,
        @RequestParam(defaultValue = "1") @ValidPage Integer pageNumber // 1이상
    ){
        return ApiResponse.onSuccess(
                ReviewSuccessCode.FOUND,
                reviewQueryService.getMyReviews(memberName, pageNumber-1)
        );
    }
}

