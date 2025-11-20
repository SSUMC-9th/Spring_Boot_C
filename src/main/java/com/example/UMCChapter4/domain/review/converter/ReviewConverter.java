package com.example.UMCChapter4.domain.review.converter;

import com.example.UMCChapter4.domain.member.entity.Member;
import com.example.UMCChapter4.domain.review.dto.ReviewReqDTO;
import com.example.UMCChapter4.domain.review.dto.ReviewResDTO;
import com.example.UMCChapter4.domain.review.entity.Review;
import com.example.UMCChapter4.domain.store.entity.Store;

import java.util.ArrayList;

public class ReviewConverter {

    //Review -> ReviewSearchDTO
    public static ReviewResDTO.ReviewSearchDTO toSearchDTO(
            String description,
            Float rate
//            List<ReviewPhoto> reviewPhotoList,
//            List<ReviewReply> reviewReplyList
    ) {
        return  ReviewResDTO.ReviewSearchDTO.builder()
                .searchDescription(description)
                .searchRate(rate)
//                .searchReviewPhotoList(reviewPhotoList)
//                .searchReviewReplyList(reviewReplyList)
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
                .rate(dto.rate())
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


}
