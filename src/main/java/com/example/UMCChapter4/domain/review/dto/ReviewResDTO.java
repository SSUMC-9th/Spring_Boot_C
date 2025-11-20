package com.example.UMCChapter4.domain.review.dto;

import lombok.Builder;

public class ReviewResDTO {

    @Builder
    public record ReviewSearchDTO(
            String searchDescription,
            Float searchRate
/*
        List<ReviewPhoto> searchReviewPhotoList;
        List<ReviewReply> searchReviewReplyList;
*/
    ){}


    @Builder
    public record ReviewWriteDTO(
            Long reviewId
    ){}

}

