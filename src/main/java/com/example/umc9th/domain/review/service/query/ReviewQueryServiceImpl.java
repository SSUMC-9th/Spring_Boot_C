package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.MyReviewDto;
import com.example.umc9th.domain.review.dto.response.MyReviewResDto;
import com.example.umc9th.domain.review.dto.response.ReviewResponseDto;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.exception.code.StoreErrorCode;
import com.example.umc9th.domain.store.exception.code.StoreException;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    @Override
    public MyReviewResDto checkMyReview(Long memberId, String storeName, Float score) {

        // Repository에서 DTO 리스트 조회
        List<MyReviewDto> myReviews = reviewRepository.checkMyReview(memberId, storeName, score);

        // Converter를 사용하여 Wrapper DTO로 변환 후 반환
        return ReviewConverter.toMyReviewResDto(myReviews);
    }

    // 가게 리뷰 조회 API(페이징)
    @Override
    public ReviewResponseDto.ReviewPreViewListDTO findReview(
        String storeName,
        Integer page
    ){
        // - 가게를 가져온다 (가게 존재 여부 검증)
        Store store = storeRepository.findByName(storeName)
                //    - 없으면 예외 터뜨린다
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        //- 가게에 맞는 리뷰를 가져온다 (Offset 페이징)
        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<Review> result = reviewRepository.findAllByStore(store, pageRequest);

        //- 결과를 응답 DTO로 변환한다 (컨버터 이용)
        return ReviewConverter.toReviewPreviewListDTO(result);
    }

    // 나의 리뷰 조회 API(페이징)
    @Override
    public ReviewResponseDto.ReviewPreViewListDTO getMyReviewList(
            Long memberId,
            Integer page
    ) {

        // 멤버를 가져온다 (멤버 존재 여부 검증)
        Member member = memberRepository.findById(memberId)
                // 없으면 예외 터뜨린다
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 사용자가 쓴 리뷰를 조회하여 가져온다 (pageSize = 10으로 고정)
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Review> reviewPage = reviewRepository.findAllByMemberId(memberId, pageRequest);

        // 결과를 응답 DTO로 변환한다 (Converter 이용)
        return ReviewConverter.toReviewPreviewListDTO(reviewPage);
    }
}
