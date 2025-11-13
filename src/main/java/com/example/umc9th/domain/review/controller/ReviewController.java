package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.response.ReviewResponseDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.query.ReviewQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewQueryService reviewQueryService;


    // query에 검색할 키워드를 넣고 type에 검색 조건을 넣음
    // type에는 region, star, both가 있고 추가할 수 있음
    @GetMapping("/review/search")
    public ApiResponse<List<Review>> searchReview(@RequestParam String query,  // 안암동
                                    @RequestParam String type // region
    ) {
        // service 에게 요청
        List<Review> result = reviewQueryService.searchReview(query, type);
        // 응답 코드 정의
        GeneralSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, result);
    }

    @GetMapping("/review/{memberId}")
    public ApiResponse<List<ReviewResponseDTO.MyReview>> getMyReviews(
            @PathVariable Long memberId,
            @RequestParam(required = false) String storeName,// 가게 이름 필터링 (선택적)
            @RequestParam(required = false) Double ratingRange) { //  null값으로 처리하기 위해 Double로 바꿈 // 별점별 (5점, 4점대, 3점대 …)

        // 응답 코드 정의
        GeneralSuccessCode code = GeneralSuccessCode.OK;

        // service 에게 요청
        List<Review> list = reviewQueryService.searchMyReview(memberId, storeName, ratingRange);

        List<ReviewResponseDTO.MyReview> result = ReviewConverter.toReviewDTOList(list);
        return ApiResponse.onSuccess(code, result);
    }

}
