package com.example.UMCChapter4.domain.review.repository;

import com.example.UMCChapter4.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}

