package com.example.umc9th.domain.review.service.command;

import com.example.umc9th.domain.review.dto.request.ReviewRequestDto;
import com.example.umc9th.domain.review.dto.response.ReviewResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface ReviewCommandService {
    ReviewResponseDto.createReview createReview(Long StoreId, ReviewRequestDto.createReview request);
}

