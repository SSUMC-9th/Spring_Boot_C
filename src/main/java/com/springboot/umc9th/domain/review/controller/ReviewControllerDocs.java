package com.springboot.umc9th.domain.review.controller;

import com.springboot.umc9th.domain.review.dto.res.ReviewResDTO;
import com.springboot.umc9th.global.annotation.CheckPage;
import com.springboot.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RequestParam;

public interface ReviewControllerDocs {
    @Operation(
            summary = "가게의 리뷰 목록 조회 API By 마크 (개발 중)",
            description = "특정 가게의 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews( String storeName,
                                                               Integer page);

    @Operation(summary = "내가 작성한 리뷰 목록 조회 API", description = "마이페이지에서 내가 작성한 리뷰를 페이징을 포함하여 조회합니다. page는 1부터 시작합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE4001", description = "페이지 번호가 유효하지 않습니다 (1 미만)."),
    })
    ApiResponse<ReviewResDTO.MyReviewPreViewListDTO> getMyReviewList(
            Long memberId,
             Integer page // 커스텀 어노테이션 적용
    );
}
