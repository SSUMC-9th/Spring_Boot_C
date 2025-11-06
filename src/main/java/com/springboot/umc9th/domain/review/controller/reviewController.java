package com.springboot.umc9th.domain.review.controller;

import com.springboot.umc9th.domain.review.dto.MyReviewResponse;
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

    private final ReviewQueryService reviewQueryService;


    @GetMapping("/search")
    public List<Review> searchReview(@RequestParam String query,@RequestParam Integer page,
                                     @RequestParam String type) {

        List<Review> result = reviewQueryService.searchReview(query,type);
        return result;
    }


    @GetMapping("/my")
    public List<MyReviewResponse> searchMyReview(
            @RequestParam Long memberId,
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String type
    ) {
        return reviewQueryService.searchMyReviews(memberId, query, type);
    }
}
