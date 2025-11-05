package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.response.ReviewResponseDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import com.example.umc9th.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewQueryService reviewQueryService;


    // query에 검색할 키워드를 넣고 type에 검색 조건을 넣음
    // type에는 region, star, both가 있고 추가할 수 있음
    @GetMapping("/review/search")
    public List<Review> searchReview(@RequestParam String query,  // 안암동
                                     @RequestParam String type // region
    ) {
        // service 에게 요청
        List<Review> result = reviewQueryService.searchReview(query, type);
        return result;
    }

    @GetMapping("/review/{memberId}")
    public List<ReviewResponseDTO> getMyReviews(
            @PathVariable Long memberId,
            @RequestParam(required = false) String storeName,// 가게 이름 필터링 (선택적)
            @RequestParam(required = false) Double ratingRange) { //  null값으로 처리하기 위해 Double로 바꿈 // 별점별 (5점, 4점대, 3점대 …)

        // service 에게 요청
        return reviewQueryService.searchMyReview(memberId, storeName, ratingRange);

    }

}
