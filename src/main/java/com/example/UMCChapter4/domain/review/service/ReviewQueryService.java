package com.example.UMCChapter4.domain.review.service;

import com.example.UMCChapter4.domain.member.entity.QMember;
import com.example.UMCChapter4.domain.review.entity.QReview;
import com.example.UMCChapter4.domain.review.entity.Review;
import com.example.UMCChapter4.domain.review.repository.ReviewQueryDslImpl;
import com.example.UMCChapter4.domain.review.repository.ReviewRepository;
import com.example.UMCChapter4.domain.store.entity.QLocation;
import com.example.UMCChapter4.domain.store.entity.QStore;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {
    public final ReviewRepository reviewRepository;

    public List<Review> searchReview(
            String query,
            String type
    ) {
        //Q클래스 정의
        QReview review = QReview.review;
        QLocation location = QLocation.location;

        //BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        //BooleanBuilder 사용

        //동적 쿼리: 검색 조건
        if (type.equals("location")) {
            builder.and(review.store.location.name.contains(query));
        }
        if (type.equals("rate")) {
            builder.and(review.rate.goe(Float.parseFloat(query)));
        }
        if (type.equals("both")) {

            // & 기준 변환
            String firstQuery = query.split("&")[0];
            String secondQuery = query.split("&")[1];

            // 동적 쿼리
            builder.and(location.name.contains(firstQuery));
            builder.and(review.rate.goe(Float.parseFloat(secondQuery)));
        }

        List<Review> reviewList = reviewRepository.searchReview(builder);
        return reviewList;
    }

    //미션 가게별 별점별 리뷰
    public List<Review> searchMyReview(
            String type, // 가게별, 별점별
            String query,
            Long memberId
    ){
        QReview review = QReview.review;
        QStore store = QStore.store;

        BooleanBuilder builder = new BooleanBuilder().and(review.member.id.eq(memberId));

        if (type.equals("store")) {
            builder.and(store.name.contains(query));
        }

        if (type.equals("rate")) {
            builder.and(review.rate.goe(Float.parseFloat(query)));
        }

        if (type.equals("both")) {

            // & 기준 변환
            String firstQuery = query.split("&")[0];
            String secondQuery = query.split("&")[1];

            // 동적 쿼리
            builder.and(store.name.contains(firstQuery));
            builder.and(review.rate.goe(Float.parseFloat(secondQuery)));
        }

        return reviewRepository.searchMyReview(builder);
    }
}
