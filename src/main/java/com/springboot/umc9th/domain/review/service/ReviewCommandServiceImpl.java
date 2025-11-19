package com.springboot.umc9th.domain.review.service;

import com.springboot.umc9th.domain.member.entity.Member;
import com.springboot.umc9th.domain.member.repository.MemberRepository;
import com.springboot.umc9th.domain.review.entity.Review;
import com.springboot.umc9th.domain.review.repository.ReviewRepository;
import com.springboot.umc9th.domain.review.converter.ReviewConverter;
import com.springboot.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.springboot.umc9th.domain.review.dto.res.ReviewResDTO;
import com.springboot.umc9th.domain.store.entity.Store;
import com.springboot.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    private static final Long FIXED_MEMBER_ID = 1L; // 하드코딩!

    @Transactional
    @Override
    public ReviewResDTO.CreateReviewResDTO createReview(Long storeId, ReviewReqDTO.CreateReviewDTO dto) {

        // 1) 하드코딩
        Member member = memberRepository.findById(FIXED_MEMBER_ID)
                .orElseThrow(() -> new RuntimeException("member not found"));

        // 2) 가게 조회
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("store not found"));

        // 3) 리뷰 생성
        Review review = Review.builder()
                .member(member)
                .store(store)
                .reviewContent(dto.content())
                .reviewScore(dto.rating())
                .build();

        // 4) 저장
        reviewRepository.save(review);

        // 5) Converter 사용한 응답 반환
        return ReviewConverter.toCreateReviewResDTO(review);
    }
}
