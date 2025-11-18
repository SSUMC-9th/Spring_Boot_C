package com.example.umc9th.domain.review.service.command;

import com.example.umc9th.domain.review.dto.request.ReviewRequestDTO;
import com.example.umc9th.domain.review.dto.response.ReviewResponseDTO;



public interface ReviewCommandService {
    public ReviewResponseDTO.createReview createReview(Long storeId, ReviewRequestDTO.createReview requestDTO);

}
