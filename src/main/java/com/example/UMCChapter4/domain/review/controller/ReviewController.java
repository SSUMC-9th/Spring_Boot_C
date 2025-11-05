package com.example.UMCChapter4.domain.review.controller;

import com.example.UMCChapter4.domain.review.entity.QReview;
import com.example.UMCChapter4.domain.review.entity.Review;
import com.example.UMCChapter4.domain.review.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    public final ReviewQueryService reviewQueryService;

    @GetMapping("/reviews/search")
    public List<Review> searchReview(
            @RequestParam String query,
            @RequestParam String type
    ) {
        //서비스에게 요청
        return reviewQueryService.searchReview(query, type);
    }

    @GetMapping("/reviews/member/search")
    public List<Review> searchMemberReview(
            @RequestParam String query,
            @RequestParam String type,
            @RequestParam Long memberId
    ) {
        List<Review> reviewList = reviewQueryService.searchMyReview(query, type, memberId);

        for (Review review : reviewList) {
            System.out.println(
                    "id: " + review.getId()
                    + ", description: " + review.getDescription()
                    + ", rate: " + review.getRate()
                    + ", memberId: " + memberId
                    + ", store: " + review.getStore()
            );
        }

        return reviewList;
    }
}

