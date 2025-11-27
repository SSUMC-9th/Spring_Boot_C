package com.example.UMCChapter4.domain.review.service.query;

import com.example.UMCChapter4.domain.member.entity.Member;
import com.example.UMCChapter4.domain.member.exception.MemberException;
import com.example.UMCChapter4.domain.member.exception.code.MemberErrorCode;
import com.example.UMCChapter4.domain.member.repository.MemberRepository;
import com.example.UMCChapter4.domain.review.converter.ReviewConverter;
import com.example.UMCChapter4.domain.review.dto.ReviewResDTO;
import com.example.UMCChapter4.domain.review.entity.QReview;
import com.example.UMCChapter4.domain.review.entity.Review;
import com.example.UMCChapter4.domain.review.exception.ReviewException;
import com.example.UMCChapter4.domain.review.exception.code.ReviewErrorCode;
import com.example.UMCChapter4.domain.review.repository.ReviewRepository;
import com.example.UMCChapter4.domain.store.entity.Store;
import com.example.UMCChapter4.domain.store.exception.StoreException;
import com.example.UMCChapter4.domain.store.exception.code.StoreErrorCode;
import com.example.UMCChapter4.domain.store.repository.StoreRepository;
import com.example.UMCChapter4.global.validator.PageValidator;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {
    public final ReviewRepository reviewRepository;
    public final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    public List<ReviewResDTO.ReviewSearchDTO> searchReview(
            String query,
            String type
    ) {
        //Q클래스 정의
        QReview review = QReview.review;
//        QLocation location = QLocation.location;

        //BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        //BooleanBuilder 사용

        //동적 쿼리: 검색 조건
        if (type.equals("location")) {
            builder.and(review.store.location.name.contains(query));
        }
        if (type.equals("rate")) {
            builder.and(review.rate.eq(new BigDecimal(query)));
        }
        if (type.equals("both")) {

            // & 기준 변환
            String firstQuery = query.split("&")[0];
            String secondQuery = query.split("&")[1];

            // 동적 쿼리
            builder.and(review.store.location.name.contains(firstQuery));
            builder.and(review.rate.eq(new BigDecimal(secondQuery)));
        }

        List<Review> reviewList = reviewRepository.searchReview(builder);
        // 임시 예외 처리
        if (reviewList.isEmpty()) {
            throw new ReviewException(ReviewErrorCode.NOT_FOUND);
        }

        // Convert
        List<ReviewResDTO.ReviewSearchDTO> ResDTO = new ArrayList<>();
        for (Review r : reviewList) {
            ResDTO.add(ReviewConverter.toSearchDTO(r));
        }

        return ResDTO;
    }

    //미션 가게별 별점별 리뷰
    public List<ReviewResDTO.ReviewSearchMyDTO> searchMyReview(
            String query, // 가게별, 별점별
            String type,
            Long memberId
    ){
        QReview review = QReview.review;

        BooleanBuilder builder = new BooleanBuilder().and(review.member.id.eq(memberId));

        if (type.equals("store")) {
            builder.and(review.store.name.contains(query));
        }
        if (type.equals("rate")) {
            builder.and(review.rate.eq(new BigDecimal(query)));
        }
        if (type.equals("both")) {

            // & 기준 변환
            String firstQuery = query.split("&")[0];
            String secondQuery = query.split("&")[1];

            // 동적 쿼리
            builder.and(review.store.name.contains(firstQuery));
            builder.and(review.rate.eq(new BigDecimal(secondQuery)));
        }

        List<Review> reviewList = reviewRepository.searchMyReview(builder);
        // 임시 예외 처리
        if (reviewList.isEmpty()) {
            throw new ReviewException(ReviewErrorCode.NOT_FOUND);
        }

        // Convert
        List<ReviewResDTO.ReviewSearchMyDTO> ResDTO = new ArrayList<>();
        for (Review r : reviewList) {
            ResDTO.add(ReviewConverter.toSearchMyDTO(r));
        }

        return ResDTO;
    }


    // 가게별 리뷰 목록 조회
    public ReviewResDTO.ReviewPreviewListDTO getReviews(
            String storeName,
            Integer pageNumber
    ){
        int PAGE_SIZE = 5; //

        // - 가게를 가져온다 (가게 존재 여부 검증)
        Store store = storeRepository.findByName(storeName)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        //- 가게에 맞는 리뷰를 가져온다 (Offset 페이징)
        PageRequest pageRequest = PageRequest.of(pageNumber, PAGE_SIZE);
        Page<Review> result = reviewRepository.findAllWithMemberByStore(store, pageRequest);

        PageValidator.validatePageRequest(result); // pageNumber 검사

        //- 결과를 응답 DTO로 변환한다 (컨버터 이용)
        return ReviewConverter.toReviewPreviewListDTO(result);
    }


    //멤버별 리뷰 목록 조회
    public ReviewResDTO.ReviewMyPreviewListDTO getMyReviews(
            String memberName,
            Integer pageNumber
    ){
        int PAGE_SIZE = 10;

        // - 가게를 가져온다 (가게 존재 여부 검증)
        Member member = memberRepository.findByName(memberName)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));



        //- 가게에 맞는 리뷰를 가져온다 (Offset 페이징)
        PageRequest pageRequest = PageRequest.of(pageNumber, PAGE_SIZE);
        Page<Review> result = reviewRepository.findAllWithMemberAndStore(member, pageRequest);

        PageValidator.validatePageRequest(result); // pageNumber 검사

        //- 결과를 응답 DTO로 변환한다 (컨버터 이용)
        return ReviewConverter.toReviewMyPreviewListDTO(result);
    }

}
