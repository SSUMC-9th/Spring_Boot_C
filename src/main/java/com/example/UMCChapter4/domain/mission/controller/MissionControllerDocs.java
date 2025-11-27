package com.example.UMCChapter4.domain.mission.controller;

import com.example.UMCChapter4.domain.mission.dto.membermission.MemberMissionReqDTO;
import com.example.UMCChapter4.domain.mission.dto.membermission.MemberMissionResDTO;
import com.example.UMCChapter4.domain.mission.dto.mission.MissionReqDTO;
import com.example.UMCChapter4.domain.mission.dto.mission.MissionResDTO;
import com.example.UMCChapter4.global.annotation.ValidPage;
import com.example.UMCChapter4.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface MissionControllerDocs {
    @Operation(
            summary = "미션 생성 API",
            description = "미션을 생성합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @PostMapping("/create")
    ApiResponse<MissionResDTO.MissionCreateDTO> createMission(
            @RequestBody MissionReqDTO.MissionCreateDTO ReqDTO
    );

    @Operation(
            summary = "가게별 미션 조회 API",
            description = "가게별 미션을 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("/store/get")
    ApiResponse<MissionResDTO.MissionPreviewListDTO> getMission(
            @RequestParam String storeName,
            @RequestParam(defaultValue = "1") @ValidPage Integer pageNumber
    );
}
