package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.response.MissionResponseDTO;
import com.example.umc9th.domain.review.dto.response.ReviewResponseDTO;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface MissionControllerDocs {
    // 가게의 리뷰 목록 조회
    @Operation(
            summary = "가게의 미션 목록 조회 API By 요시 (개발 중)",
            description = "특정 가게의 미션을 모두 조회합니다."
    )
    @ApiResponses({@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @PostMapping("/missions")
    public ApiResponse<MissionResponseDTO.MissionPreViewListDTO> findMission(
            @RequestParam String storeName,
            @RequestParam Integer page
    );
}
