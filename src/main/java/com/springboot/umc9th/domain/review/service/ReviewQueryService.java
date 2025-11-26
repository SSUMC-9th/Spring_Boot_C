package com.springboot.umc9th.domain.review.service;

import com.springboot.umc9th.domain.review.dto.MyReviewResponse;
import com.springboot.umc9th.domain.review.dto.res.ReviewResDTO;
import com.springboot.umc9th.domain.review.entity.Review;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ReviewQueryService {


    List<Review> searchReview(String query, String type);

    List<MyReviewResponse> searchMyReviews(Long memberId, String query, String type);

    ReviewResDTO.ReviewPreViewListDTO findReview(
            @RequestParam String storeName
            , @RequestParam Integer page);

    ReviewResDTO.MyReviewPreViewListDTO getMyReviewList(Long memberId, Integer page);
}
