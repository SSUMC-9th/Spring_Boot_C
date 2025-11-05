package com.springboot.umc9th.domain.review.controller;

import com.springboot.umc9th.domain.review.entity.Review;
import com.springboot.umc9th.domain.review.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/reviews")
public class reviewController {

    private final ReviewQueryService reviewQueryService; // ✅ 의존성 주입


    @GetMapping("/search")
    public List<Review> searchReview(@RequestParam String query,@RequestParam Integer page,
                                     @RequestParam String type) {

        List<Review> result = reviewQueryService.searchReview(query,type);
        return result;
    }


    @GetMapping("/my")
    public List<Review> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer score
    ) {
        return reviewQueryService.searchMyReviews(memberId, storeName, score);
    }
}
