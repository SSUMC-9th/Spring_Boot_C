package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.dto.response.ReviewResponseDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface ReviewQueryDSL {

    // 검색 API
    List<Review> searchReview(Predicate predicate);

    // 내가 작성한 리뷰 보기 API
    List<ReviewResponseDTO> searchMyReview(Predicate predicate);


}
