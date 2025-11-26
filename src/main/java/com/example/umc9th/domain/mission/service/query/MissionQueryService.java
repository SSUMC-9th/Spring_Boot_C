package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.mission.dto.response.MissionResponseDTO;
import com.example.umc9th.domain.review.dto.response.ReviewResponseDTO;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.List;

public interface MissionQueryService {
    // 특정 가게의 미션 목록
    MissionResponseDTO.MissionPreViewListDTO findMission(String storeName, Integer page);
}
