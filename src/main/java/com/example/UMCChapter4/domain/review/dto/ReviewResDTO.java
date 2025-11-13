package com.example.UMCChapter4.domain.review.dto;

import com.example.UMCChapter4.domain.review.entity.Review;
import com.example.UMCChapter4.domain.review.entity.ReviewPhoto;
import com.example.UMCChapter4.domain.review.entity.ReviewReply;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class ReviewResDTO {

    @Builder
    @Getter
    public static class Searching {
        private String searchDescription;
        private Float searchRate;
//        private List<ReviewPhoto> searchReviewPhotoList;
//        private List<ReviewReply> searchReviewReplyList;
    }

//    @Builder
//    @Getter
//    public static class SearchingMyReview {
//        private String searchDescription;
//        private Float searchRate;
//        private List<ReviewPhoto> reviewPhotoList;
//        private List<ReviewReply> reviewReplyList;
//    }

    @Builder
    @Getter
    public static class Writing {

    }

}

