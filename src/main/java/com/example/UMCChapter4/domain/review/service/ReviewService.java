package com.example.UMCChapter4.domain.review.service;

import com.example.UMCChapter4.domain.member.entity.Member;
import com.example.UMCChapter4.domain.member.repository.MemberRepository;
import com.example.UMCChapter4.domain.review.dto.WriteReviewDto;
import com.example.UMCChapter4.domain.review.entity.Review;
import com.example.UMCChapter4.domain.review.repository.ReviewRepository;
import com.example.UMCChapter4.domain.store.entity.Store;
import com.example.UMCChapter4.domain.store.repository.StoreRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    // 리뷰 작성 메서드
    @Transactional
    public Long write(WriteReviewDto dto) {
        Member member = memberRepository.findById(dto.memberId())
                .orElseThrow(() -> new EntityNotFoundException("member not found"));
        Store store = storeRepository.findById(dto.storeId())
                .orElseThrow(() -> new EntityNotFoundException("store not found"));

        Review review = Review.createReview(
                dto.description(),
                dto.rate(),
                member,
                store
        );

        reviewRepository.save(review);
        return review.getId();
    }
}
