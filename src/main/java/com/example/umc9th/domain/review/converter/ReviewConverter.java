package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.review.dto.MyReviewDto;
import com.example.umc9th.domain.review.dto.request.ReviewRequestDto;
import com.example.umc9th.domain.review.dto.response.MyReviewResDto;
import com.example.umc9th.domain.review.dto.response.ReviewResponseDto;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewConverter {

    // MyReviewDto를 MyReviewResDto로 변환
    public static MyReviewResDto toMyReviewResDto(List<MyReviewDto> reviewList) {
        return MyReviewResDto.builder()
                .reviewList(reviewList)
                .listSize(reviewList.size())
                .build();
    }

    // ReviewRequestDto를 Review 엔티티로 변환
    public static Review toReview(Long storeId, ReviewRequestDto.createReview request) {
        return Review.builder()
                .content(request.content())
                .ratingScore(request.rating_score())
                .store(Store.builder().id(storeId).build())
                .member(Member.builder().id(request.memberId()).build())
                .build();
    }

    // Review 엔티티를 ReviewResponseDto(createReview)로 변환
    public static ReviewResponseDto.createReview toCreateReview(Review review) {
        return new ReviewResponseDto.createReview(
                review.getId(),
                review.getCreatedAt()
        );
    }

    // 가게 리뷰 조회
    //  result -> DTO
    public static ReviewResponseDto.ReviewPreViewListDTO toReviewPreviewListDTO(
            Page<Review> result
    ){
        return ReviewResponseDto.ReviewPreViewListDTO.builder()
                .reviewList(result.getContent().stream()
                        .map(ReviewConverter::toReviewPreviewDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    // review 객체 -> ReviewPreViewDTO로 변환
    public static ReviewResponseDto.ReviewPreViewDTO toReviewPreviewDTO(
            Review review
    ){
        return ReviewResponseDto.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getRatingScore())
                .body(review.getContent())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }
}