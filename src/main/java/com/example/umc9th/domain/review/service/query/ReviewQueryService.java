package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.review.dto.response.MyReviewResDto;
import com.example.umc9th.domain.review.dto.response.ReviewResponseDto;



public interface ReviewQueryService {
    MyReviewResDto checkMyReview(Long memberId, String storeName, Float score);


    // 가게 리뷰 조회 API(페이징)
    ReviewResponseDto.ReviewPreViewListDTO findReview(
        String storeName,
        Integer page
    );

    // 나의 리뷰 조회 API (페이징)
    ReviewResponseDto.ReviewPreViewListDTO getMyReviewList(
            Long MemberId,
            Integer page
    );
}