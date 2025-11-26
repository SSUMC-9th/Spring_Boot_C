package com.example.UMCChapter4.domain.review.repository;

import com.example.UMCChapter4.domain.member.entity.Member;
import com.example.UMCChapter4.domain.review.entity.Review;
import com.example.UMCChapter4.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review,Long>, ReviewQueryDsl {
    @Query(
            value = "SELECT r FROM Review r " +
                    "JOIN FETCH r.member " +
                    "WHERE r.store = :store",
            countQuery = "SELECT COUNT(r) FROM Review r WHERE r.store = :store"
    )
    Page<Review> findAllWithMemberByStore(@Param("store") Store store, Pageable pageable);

    @Query(
            value = "SELECT r FROM Review r " +
                    "JOIN FETCH r.member " +
                    "JOIN FETCH r.store " +
                    "WHERE r.member = :member",
            countQuery = "SELECT COUNT(r) FROM Review r WHERE r.member = :member"
    )
    Page<Review> findAllWithMemberAndStore(@Param("member") Member member, Pageable pageable);

}

