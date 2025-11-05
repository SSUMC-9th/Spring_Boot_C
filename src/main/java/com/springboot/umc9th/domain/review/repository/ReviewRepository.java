package com.springboot.umc9th.domain.review.repository;

import com.springboot.umc9th.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> , ReviewQueryDsl{

    /*   리뷰 작성은
         JpaRepository<Review, Long>
         save(Review review) 메서드를 사용가능 */


}
