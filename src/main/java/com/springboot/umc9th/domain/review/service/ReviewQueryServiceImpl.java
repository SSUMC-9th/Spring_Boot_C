package com.springboot.umc9th.domain.review.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.springboot.umc9th.domain.member.entity.Member;
import com.springboot.umc9th.domain.member.repository.MemberRepository;
import com.springboot.umc9th.domain.review.converter.ReviewConverter;
import com.springboot.umc9th.domain.review.dto.MyReviewResponse;
import com.springboot.umc9th.domain.review.dto.res.ReviewResDTO;
import com.springboot.umc9th.domain.review.entity.QReview;
import com.springboot.umc9th.domain.review.entity.Review;
import com.springboot.umc9th.domain.review.repository.ReviewRepository;
import com.springboot.umc9th.domain.store.entity.Store;
import com.springboot.umc9th.domain.store.exception.StoreException;
import com.springboot.umc9th.domain.store.exception.code.StoreErrorCode;
import com.springboot.umc9th.domain.store.repository.StoreRepository;

import lombok.RequiredArgsConstructor; // [중요] 이거 추가
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // [권장] 추가
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;

    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;




    @Override
    public List<Review> searchReview(String query, String type) {
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        if (type.equals("location")) {
            builder.and(review.store.local.name.contains(query));
        }
        if (type.equals("star")) {
            builder.and(review.reviewScore.goe(Float.parseFloat(query)));
        }
        if(type.equals("both")) {
            String firstQuery = query.split("&")[0];
            String secondQuery = query.split("&")[1];

            builder.and(review.store.local.name.contains(firstQuery));
            builder.and(review.reviewScore.goe(Float.parseFloat(secondQuery)));
        }
        // searchReview가 QueryDSL용 커스텀 메서드라면 그대로 둡니다.
        List<Review> reviewList = reviewRepository.searchReview(builder);
        return reviewList;
    }

    @Override
    public List<MyReviewResponse> searchMyReviews(Long memberId, String query, String type) {
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(review.member.id.eq(memberId));

        if (type != null) { // type이 null일 수도 있으니 안전하게 처리
            if (type.equals("storeName")) {
                builder.and(review.store.local.name.contains(query));
            }
            else if (type.equals("score")) {
                builder.and(review.reviewScore.eq(Integer.parseInt(query)));
            }
            else if(type.equals("both")) {
                String storeNameQuery = query.split("&")[0];
                String scoreQuery = query.split("&")[1];

                builder.and(review.store.local.name.contains(storeNameQuery));
                builder.and(review.reviewScore.eq(Integer.parseInt(scoreQuery)));
            }
        }

        Predicate predicate = builder;
        return reviewRepository.searchMyReview(predicate);
    }

    @Override
    public ReviewResDTO.ReviewPreViewListDTO findReview(String storeName, Integer page){ // @RequestParam 제거 (Service에는 필요 없음)
        // 1. 가게 존재 여부 검증
        Store store = storeRepository.findByStoreName(storeName)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        // 2. 페이징 (page - 1 처리 확인 필요: 프론트가 1부터 주면 -1 해야 함)
        // 일단 기존 코드 유지: page 그대로 사용
        PageRequest pageRequest = PageRequest.of(page - 1, 10); // 보통 여기서 -1을 많이 합니다. 확인해보세요!

        Page<Review> result = reviewRepository.findAllByStore(store, pageRequest);

        return ReviewConverter.toReviewPreviewListDTO(result);
    }

    @Override
    public ReviewResDTO.MyReviewPreViewListDTO getMyReviewList(Long memberId, Integer page) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("멤버를 찾을 수 없습니다."));

        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        Page<Review> reviewPage = reviewRepository.findAllByMember(member, pageRequest);

        return ReviewConverter.toMyReviewPreViewListDTO(reviewPage);
    }
}