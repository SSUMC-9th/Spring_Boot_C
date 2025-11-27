package com.example.UMCChapter4.domain.review.converter;

import com.example.UMCChapter4.domain.member.entity.Member;
import com.example.UMCChapter4.domain.review.dto.ReviewReqDTO;
import com.example.UMCChapter4.domain.review.dto.ReviewResDTO;
import com.example.UMCChapter4.domain.review.entity.Review;
import com.example.UMCChapter4.domain.store.entity.Store;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReviewConverter {

    //Review -> ReviewSearchDTO
    public static ReviewResDTO.ReviewSearchDTO toSearchDTO(
            Review review
    ) {
        return ReviewResDTO.ReviewSearchDTO.builder()
                .reviewId(review.getId())
                .build();
    }

    //Review -> ReviewSearchMyDTO
    public static ReviewResDTO.ReviewSearchMyDTO toSearchMyDTO(
            Review review
    ) {
        return ReviewResDTO.ReviewSearchMyDTO.builder()
                .reviewId(review.getId())
                .build();
    }

    //ReviewWriteDTO -> Review
    public static Review toReview(
            ReviewReqDTO.ReviewWriteDTO dto,
            Member member,
            Store store
    ) {
        return Review.builder()
                .description(dto.description())
                .rate(new BigDecimal(dto.rate()))
                .store(store)
                .member(member)
                .reviewPhotoList(new ArrayList<>())
                .reviewReplyList(new ArrayList<>())
                .build();
    }

    //Review -> ReviewWriteDTO
    public static ReviewResDTO.ReviewWriteDTO toWriteDTO(
            Review review
    ){
        return ReviewResDTO.ReviewWriteDTO.builder()
                .reviewId(review.getId())
                .build();
    }

    // Page<Review> -> ReviewPreViewListDTO
    public static ReviewResDTO.ReviewPreviewListDTO toReviewPreviewListDTO(
            Page<Review> result
    ){
        return ReviewResDTO.ReviewPreviewListDTO.builder()
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

    // Review -> ReviewPreviewDTO
    public static ReviewResDTO.ReviewPreviewDTO toReviewPreviewDTO(
            Review review
    ){
        return ReviewResDTO.ReviewPreviewDTO.builder()
                .nickname(review.getMember().getName())
                .rate(review.getRate().toString())
                .description(review.getDescription())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }


    public static ReviewResDTO.ReviewMyPreviewListDTO toReviewMyPreviewListDTO(
            Page<Review> result
    ) {
        return ReviewResDTO.ReviewMyPreviewListDTO.builder()
                .reviewList(result.getContent().stream()
                        .map(ReviewConverter::toReviewMyPreviewDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static ReviewResDTO.ReviewMyPreviewDTO toReviewMyPreviewDTO(
            Review review
    ){
        return ReviewResDTO.ReviewMyPreviewDTO.builder()
                .nickname(review.getMember().getName())
                .rate(review.getRate().toString())
                .description(review.getDescription())
                .storeName(review.getStore().getName())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }
}
