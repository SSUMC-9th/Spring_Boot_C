package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.response.MissionResponseDto;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


public interface MissionControllerDocs {
    @Operation(
            summary = "특정 가게의 미션 목록 조회 API By 제스퍼 (개발 중)",
            description = "특정 가게의 미션 목록을 조회합니다. Query Parameter로 page 번호를 전달하며, 1부터 시작합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK, 성공적으로 조회되었습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 요청입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })public ApiResponse<MissionResponseDto.MissionPreViewListDTO> getMissionListByStore(Long storeId, Integer page);
}
