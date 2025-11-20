package com.springboot.umc9th.domain.review.service;

import com.springboot.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.springboot.umc9th.domain.review.dto.res.ReviewResDTO;
import org.springframework.transaction.annotation.Transactional;

public interface ReviewCommandService {
    @Transactional
    ReviewResDTO.CreateReviewResDTO createReview(Long storeId, ReviewReqDTO.CreateReviewDTO dto);
}
