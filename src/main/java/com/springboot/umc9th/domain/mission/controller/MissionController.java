package com.springboot.umc9th.domain.mission.controller;

import com.springboot.umc9th.domain.mission.dto.res.MissionChallengeResDTO;
import com.springboot.umc9th.domain.mission.exception.code.MissionSuccessCode;
import com.springboot.umc9th.domain.mission.service.MissionCommandService;
import com.springboot.umc9th.domain.mission.dto.req.MissionReqDTO;
import com.springboot.umc9th.domain.mission.dto.res.MissionResDTO;
import com.springboot.umc9th.domain.store.exception.code.StoreSuccessCode;
import com.springboot.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
@Tag(name = "미션 API", description = "미션 관련 api")
public class MissionController {

    private final MissionCommandService missionCommandService;

    @PostMapping("/{missionId}/challenge")
    @Operation(summary = "유저미션 추가", description = "가게의 미션을 도전 중인 미션에 추가")
    public ApiResponse<MissionChallengeResDTO.ChallengeResDTO> challenge(
            @PathVariable Long missionId
    ){
        return ApiResponse.onSuccess(
                MissionSuccessCode.CHALLENGE_CREATED,
                missionCommandService.challengeMission(missionId)
        );
    }

    @PostMapping("/{storeId}/missions")
    @Operation(summary = "가게에 미션 등록", description = "가게에 미션 추가하기 API")
    public ApiResponse<MissionResDTO.CreateMissionResDTO> createMission(
            @PathVariable Long storeId,
            @RequestBody MissionReqDTO.CreateDTO dto
    ){
        return ApiResponse.onSuccess(
                MissionSuccessCode.MISSION_CREATED,
                missionCommandService.createMission(storeId, dto)
        );
    }

}

