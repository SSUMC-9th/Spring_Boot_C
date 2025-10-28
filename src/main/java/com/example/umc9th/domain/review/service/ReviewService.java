package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public void createReview(Integer userId, Long storeId, float rating, String content) {

        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저 없음"));
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("가게 없음"));

        Review newReview = Review.builder()
                .member(member)
                .store(store)
                .ratingScore(rating)
                .content(content)
                .status(true)
                .build();

        reviewRepository.save(newReview);
    }

}
