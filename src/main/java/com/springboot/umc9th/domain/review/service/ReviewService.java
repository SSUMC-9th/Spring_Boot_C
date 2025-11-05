package com.springboot.umc9th.domain.review.service;

import com.springboot.umc9th.domain.review.dto.MyReviewResponse;
import com.springboot.umc9th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;


}
