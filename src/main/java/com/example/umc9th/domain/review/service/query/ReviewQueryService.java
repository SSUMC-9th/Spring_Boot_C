package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.MyReviewDto;
import com.example.umc9th.domain.review.dto.response.MyReviewResDto;
import com.example.umc9th.domain.review.dto.response.ReviewResponseDto;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewQueryService {
    MyReviewResDto checkMyReview(Long memberId, String storeName, Float score);


    // 가게 리뷰 조회 API(페이징)
    ReviewResponseDto.ReviewPreViewListDTO findReview(
        String storeName,
        Integer page
    );
}