package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.response.MemberMissionResponseDTO;
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
    @GetMapping("/missions")
    public ApiResponse<MissionResponseDTO.MissionPreViewListDTO> findMission(
            @RequestParam String storeName,
            @RequestParam Integer page
    );


    // 가게의 리뷰 목록 조회
    @Operation(
            summary = "진행 중인 미션 목록 조회 API By 요시 (개발 중)",
            description = "내가 진행 중인 미션을 모두 조회합니다."
    )
    @ApiResponses({@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("/missions/my/in-progress")
    public ApiResponse<MemberMissionResponseDTO.MemberMissionPreViewListDTO> findMyMissionInProgress(
            @RequestParam(defaultValue = "1") Integer page
    );

    // 내가 진행중인 미션 목록
    @Operation(
            summary = "완료된 미션 목록 조회 API By 요시 (개발 중)",
            description = "나의 완료된 미션을 모두 조회합니다."
    )
    @ApiResponses({@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("/missions/my/completed")
    ApiResponse<MemberMissionResponseDTO.MemberMissionPreViewListDTO> findMyMissionCompleted(
            @RequestParam(defaultValue = "1") Integer page
    );

    // 진행중인 미션 진행 완료로 바꾸기 (변경하고, 변경된 상태의 미션을 조회까지 해야 함)
    @Operation(
            summary = "진행 중인 미션->성공 미션 API By 요시 (개발 중)",
            description = "진행 중인 미션을 성공 상태로 바꿉니다."
    )
    @ApiResponses({@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @PostMapping("/missions/complete")
    ApiResponse<MemberMissionResponseDTO.MemberMissionPreViewDTO> completeMission(
            @RequestParam Long missionId);
}
