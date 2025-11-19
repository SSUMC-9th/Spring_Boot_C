package com.example.UMCChapter4.domain.review.service.command;

import com.example.UMCChapter4.domain.member.entity.Member;
import com.example.UMCChapter4.domain.member.exception.MemberException;
import com.example.UMCChapter4.domain.member.exception.code.MemberErrorCode;
import com.example.UMCChapter4.domain.member.repository.MemberRepository;
import com.example.UMCChapter4.domain.review.converter.ReviewConverter;
import com.example.UMCChapter4.domain.review.dto.ReviewReqDTO;
import com.example.UMCChapter4.domain.review.dto.ReviewResDTO;
import com.example.UMCChapter4.domain.review.entity.Review;
import com.example.UMCChapter4.domain.review.repository.ReviewRepository;
import com.example.UMCChapter4.domain.store.entity.Store;
import com.example.UMCChapter4.domain.store.exception.StoreException;
import com.example.UMCChapter4.domain.store.exception.code.StoreErrorCode;
import com.example.UMCChapter4.domain.store.repository.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    // 리뷰 작성 메서드
    @Transactional
    public ReviewResDTO.ReviewWriteDTO writeReview(ReviewReqDTO.ReviewWriteDTO ReqDTO) {

        // Member, Store dto id 기반으로 찾기(예외처리)
        Member member = memberRepository.findById(1L) // memberId=1 로 하드코딩
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
        Store store = storeRepository.findById(ReqDTO.storeId())
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        Review review = ReviewConverter.toReview(ReqDTO, member, store);

        reviewRepository.save(review);

        member.getReviewList().add(review);
        store.getReviewList().add(review);

        return ReviewConverter.toWriteDTO(review);

    }


}
