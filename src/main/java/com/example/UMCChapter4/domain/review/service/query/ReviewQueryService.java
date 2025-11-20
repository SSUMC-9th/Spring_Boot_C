package com.example.UMCChapter4.domain.review.service.query;

import com.example.UMCChapter4.domain.review.entity.Review;

import java.util.List;

public interface ReviewQueryService {
    List<Review> searchReview(String query, String type);
    List<Review> searchMyReview(String query, String type, Long memberId);
}
