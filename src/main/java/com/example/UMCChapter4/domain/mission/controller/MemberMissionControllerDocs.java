package com.example.UMCChapter4.domain.mission.controller;

import com.example.UMCChapter4.domain.member.dto.MemberReqDTO;
import com.example.UMCChapter4.domain.member.dto.MemberResDTO;
import com.example.UMCChapter4.domain.mission.dto.membermission.MemberMissionReqDTO;
import com.example.UMCChapter4.domain.mission.dto.membermission.MemberMissionResDTO;
import com.example.UMCChapter4.global.annotation.ValidPage;
import com.example.UMCChapter4.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface MemberMissionControllerDocs {
    @Operation(
            summary = "멤버-미션 등록 API",
            description = "해당 미션을 멤버 미션으로 등록합니다"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @PostMapping("/create")
    ApiResponse<MemberMissionResDTO.MemberMissionInProgressDTO> createMemberMission(
            @RequestBody MemberMissionReqDTO.MemberMissionCreateDTO ReqDTO
    );

    @Operation(
            summary = "멤버-미션 진행완료 API",
            description = "해당 미션을 진행완료 미션으로 변경하고, 진행 중 미션을 새롭게 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @PostMapping("/complete")
    ApiResponse<MemberMissionResDTO.MemberMissionCompleteDTO> completeMemberMission(
            @RequestBody MemberMissionReqDTO.MemberMissionCompleteDTO ReqDTO
    );

    @Operation(
            summary = "멤버-미션 상태별 조회 API",
            description = "상태별 멤버-미션을 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("/status/get")
    ApiResponse<MemberMissionResDTO.MemberMissionStatusListDTO> getStatusMemberMission(
            @RequestParam String memberName,
            @RequestParam String status,
            @RequestParam(defaultValue = "1") @ValidPage Integer pageNumber

    );
}
