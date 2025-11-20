package com.example.UMCChapter4.domain.review.service.command;

import com.example.UMCChapter4.domain.review.dto.ReviewReqDTO;
import com.example.UMCChapter4.domain.review.dto.ReviewResDTO;

public interface ReviewCommandService {
    ReviewResDTO.ReviewWriteDTO writeReview(ReviewReqDTO.ReviewWriteDTO ReqDTO);
}
