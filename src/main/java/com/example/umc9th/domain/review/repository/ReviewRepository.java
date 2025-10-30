package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    //  리뷰 작성 쿼리 JpaRepository에 있는 save() 메서드를 사용
}
