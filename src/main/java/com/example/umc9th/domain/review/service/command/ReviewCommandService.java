package com.example.umc9th.domain.review.service.command;

import com.example.umc9th.domain.review.dto.request.ReviewRequestDto;
import com.example.umc9th.domain.review.entity.Review;
import org.springframework.stereotype.Service;

@Service
public interface ReviewCommandService {
    Review createReview(Long memberId, ReviewRequestDto request);
}

