package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.request.MissionRequestDTO;
import com.example.umc9th.domain.mission.dto.response.MissionResponseDTO;
import com.example.umc9th.domain.mission.dto.response.MyMissionResponseDTO;
import com.example.umc9th.domain.mission.service.command.MissionCommandService;
import com.example.umc9th.global.annotation.ExistStore;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
public class MissionController {
    private final MissionCommandService missionCommandService;

    // 가게에 미션 추가하기 API
    @PostMapping("/{storeId}/missions")
    public ApiResponse<MissionResponseDTO.createMission> createMission(@RequestBody @Valid MissionRequestDTO.createMission requestDTO, @PathVariable @ExistStore Long storeId) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missionCommandService.createMission(storeId, requestDTO));
    }

    // 가게의 미션을 도전 중인 미션에 추가(미션 도전하기) API
    @PostMapping("/members/{memberId}/missions/{missionId}/challenge")
    public ApiResponse<MissionResponseDTO.memberMissionDTO> challengeMission(@PathVariable Long memberId, @PathVariable Long missionId) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missionCommandService.challengeMission(memberId, missionId));

    }
}
