package com.example.UMCChapter4.domain.review.converter;

import com.example.UMCChapter4.domain.review.dto.ReviewResDTO;
import com.example.UMCChapter4.domain.review.entity.ReviewPhoto;
import com.example.UMCChapter4.domain.review.entity.ReviewReply;

import java.util.List;

public class ReviewConverter {

    //객체 -> DTO
    public static ReviewResDTO.Searching toSearchingDTO(
            String description,
            Float rate
//            List<ReviewPhoto> reviewPhotoList,
//            List<ReviewReply> reviewReplyList
    ) {
        return  ReviewResDTO.Searching.builder()
                .searchDescription(description)
                .searchRate(rate)
//                .searchReviewPhotoList(reviewPhotoList)
//                .searchReviewReplyList(reviewReplyList)
                .build();
    }
}
