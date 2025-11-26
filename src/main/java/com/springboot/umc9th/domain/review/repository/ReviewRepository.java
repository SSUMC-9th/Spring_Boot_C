package com.springboot.umc9th.domain.review.repository;

import com.springboot.umc9th.domain.member.entity.Member;
import com.springboot.umc9th.domain.review.entity.Review;
import com.springboot.umc9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {

    Page<Review> findAllByStore(Store store, PageRequest pageRequest);

    /*   리뷰 작성은
         JpaRepository<Review, Long>
         save(Review review) 메서드를 사용가능 */

    Page<Review> findAllByMember(Member member, Pageable pageable);
}
