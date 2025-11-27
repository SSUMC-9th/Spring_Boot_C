package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.umc9th.domain.review.entity.Review;


import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {

    // 여러개의 리뷰를 모두 가져오므로 Page<Review>로 반환 (페이지네이션)
    Page<Review> findAllByStore(Store store, Pageable pageable);

    Page<Review> findAllByMemberId(Long memberId, Pageable pageable);
}
