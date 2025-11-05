package com.springboot.umc9th.domain.review.repository;

import com.querydsl.core.types.Predicate;
import com.springboot.umc9th.domain.review.entity.QReview;
import com.springboot.umc9th.domain.review.entity.Review;

import java.util.List;

public interface ReviewQueryDsl {
    List<Review> searchReview(
            Predicate   predicate
    );
    List<Review> searchMyReview(
            Predicate   predicate
    );
}
