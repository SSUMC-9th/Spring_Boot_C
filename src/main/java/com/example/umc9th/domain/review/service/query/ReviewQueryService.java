package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.review.dto.response.ReviewResponseDTO;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.List;

public interface ReviewQueryService {
    // 검색 API
    List<Review> searchReview(String query, String type) throws Exception;
    public List<Review> searchMyReview(Long memberId, String storeName, Double ratingRange);
    // 별점 범위 조건 함수
    public BooleanExpression ratingBetween(QReview review, Double ratingRange);

    public ReviewResponseDTO.ReviewPreViewListDTO findReview(String storeName, Integer page);
    public ReviewResponseDTO.ReviewPreViewListDTO findMyReview(Long memberId, Integer page);

}
