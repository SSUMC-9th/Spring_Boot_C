package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.Store;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDSL {

    //  리뷰 작성 쿼리 JpaRepository에 있는 save() 메서드를 사용

    // 가게에 맞는 리뷰 가져오기
    Page<Review> findAllByStore(Store store, PageRequest pageRequest);
}
