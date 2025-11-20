package com.example.umc9th.domain.review.service.command;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.request.ReviewRequestDTO;
import com.example.umc9th.domain.review.dto.response.ReviewResponseDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public ReviewResponseDTO.createReview createReview(Long storeId, ReviewRequestDTO.createReview requestDTO) {
        Review review = ReviewConverter.toReview(storeId, requestDTO);
        reviewRepository.save(review);
        return ReviewConverter.toCreateDTO(review);
    }
}
