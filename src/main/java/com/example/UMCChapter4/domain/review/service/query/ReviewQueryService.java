package com.example.UMCChapter4.domain.review.service.query;

import com.example.UMCChapter4.domain.review.dto.ReviewResDTO;

import java.util.List;

public interface ReviewQueryService {
    List<ReviewResDTO.ReviewSearchDTO> searchReview(String query, String type);
    List<ReviewResDTO.ReviewSearchMyDTO> searchMyReview(String query, String type, Long memberId);
    ReviewResDTO.ReviewPreviewListDTO getReviews(String storeName, Integer pageNumber);
    ReviewResDTO.ReviewMyPreviewListDTO getMyReviews(String memberName, Integer pageNumber);
}
