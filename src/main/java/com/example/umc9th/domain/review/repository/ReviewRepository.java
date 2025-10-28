package com.example.umc9th.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
