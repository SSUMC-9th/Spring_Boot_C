package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.QMember;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.QStore;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.umc9th.domain.store.entity.QRegion.region;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;


    public List<Review> searchReview(String query, String type) {
        // Q클래스 정의
        QReview review = QReview.review;
        QStore store = QStore.store;

        // booleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        // booleanBuilder 사용

        // 동적 쿼리: 검색 조건
        if (type.equals("region")) {
            // WHERE region_name LIKE '%query%
            builder.and(region.name.contains(query));
        }
        if (type.equals("star")) {
            // query 값 이상의 별점(star)을 가진 리뷰를 찾음 (WHERE star >= query)
            builder.and(review.star.goe(Float.parseFloat(query)));
        }
        if (type.equals("both")) {
            // 두 가지 조건(지역 + 별점)을 동시에 만족하는 리뷰
            // & 기준 변환
            String firstQuery = query.split("&")[0];
            String secondQuery = query.split("&")[1];

            // 동적 쿼리
            //builder.and(review.store.region.name.contains(firstQuery));
            builder.and(region.name.contains(firstQuery));
            builder.and(review.star.goe(Float.parseFloat(secondQuery)));

        }

        // 가게명으로 검색
        if (type.equals("store")) {
            builder.and(review.store.name.contains(query));

        }
        // Repository 사용 & 결과 매핑
        List<Review> reviewList = reviewRepository.searchReview(builder);

        // return
        return reviewList;

    }

    public List<Review> searchMyReview(Long memberId, String storeName, Double ratingRange) {

        // Q클래스 정의
        QReview review = QReview.review;
        QStore store = QStore.store;
        QMember member = QMember.member;

        // booleanBuilder 정의
        // memberId는 필수 조건으로 builder를 시작
        // builder는 이미 'r.member_id = [memberId]' 조건을 가지고 시작
        // 회원 존재 여부 확인
        memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(MemberErrorCode.MEMBER_NOT_FOUND));

        BooleanBuilder builder = new BooleanBuilder(review.member.id.eq(memberId));

        // booleanBuilder 사용

        // 동적 쿼리: 검색 조건
        // 1. 가게 이름 필터링
        if (storeName != null) {
            builder.and(store.name.contains(storeName));
        }
        // 2. 별점 범위 필터링 (Integer)
        if (ratingRange != null) {
            builder.and(ratingBetween(review, ratingRange)); // 별점 범위 조건 함수 호출
        }

        // Repository 사용 & 결과 매핑
        // Repository 호출 시 Predicate 형태로 전달
        // Repository 메서드를 QueryDSL 구현체 메서드로 변경
        return reviewRepository.searchMyReview(builder); // searchMyReview는 Predicate를 인자로 받음

    }

    // 별점 범위 조건 함수
    private BooleanExpression ratingBetween(QReview review, Double ratingRange) {
        double lowerBound = ratingRange.doubleValue(); // ratingRange=4 이면 최소 4.0부터 시작

        if (ratingRange == 5) {
            // goe: QueryDSL 에서 "크거나 같다" 는 조건을 생성
            return review.star.goe(5.0); // 5.0 이상
        } else if (ratingRange > 0) {
            double upperBound = lowerBound + 1.0;
            // lowerBound 이상이고 upperBound 미만 (예: 4.0 <= rating < 5.0)
            return review.star.goe(lowerBound).and(review.star.lt(upperBound));
        }
        return null;
    }


}
