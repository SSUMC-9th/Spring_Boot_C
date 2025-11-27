package com.example.UMCChapter4.domain.review.controller;

import com.example.UMCChapter4.domain.review.dto.ReviewReqDTO;
import com.example.UMCChapter4.domain.review.dto.ReviewResDTO;
import com.example.UMCChapter4.global.annotation.ValidPage;
import com.example.UMCChapter4.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ReviewControllerDocs {
    @Operation(
            summary = "지역별/별점별 리뷰 검색 API",
            description = "지역별/별점별 리뷰를 모두 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("/search")
    ApiResponse<List<ReviewResDTO.ReviewSearchDTO>> searchReview(
            @RequestParam String query,
            @RequestParam String type
    );

    @Operation(
            summary = "가게별/별점별 멤버의 리뷰 검색 API",
            description = "가게별/별점별 멤버의 리뷰를 모두 조회합니다"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("/my/search")
    ApiResponse<List<ReviewResDTO.ReviewSearchMyDTO>> searchMemberReview(
            @RequestParam String query,
            @RequestParam String type,
            @RequestParam Long memberId
    );

    @Operation(
            summary = "리뷰 작성 API",
            description = "특정 가게에 리뷰를 작성합니다(memberId=1 로 하드코딩)."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @PostMapping("/write")
    ApiResponse<ReviewResDTO.ReviewWriteDTO> writeReview(
            @RequestBody ReviewReqDTO.ReviewWriteDTO ReqDTO // storeName, description, rate
    );

    @Operation(
            summary = "가게의 리뷰 목록 조회 API",
            description = "특정 가게의 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("/store/get")
    ApiResponse<ReviewResDTO.ReviewPreviewListDTO> getReviews(
            @RequestParam String storeName,
            @RequestParam @ValidPage Integer pageNumber
    );


    @Operation(
            summary = "멤버의 리뷰 목록 조회 API",
            description = "특정 멤버의 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("/member/get")
    ApiResponse<ReviewResDTO.ReviewMyPreviewListDTO> getMyReviews(
            @RequestParam String memberName,
            @RequestParam @ValidPage Integer pageNumber
    );

}
