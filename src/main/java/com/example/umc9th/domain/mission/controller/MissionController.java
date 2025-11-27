package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.request.MissionRequestDTO;
import com.example.umc9th.domain.mission.dto.response.MemberMissionResponseDTO;
import com.example.umc9th.domain.mission.dto.response.MissionResponseDTO;
import com.example.umc9th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc9th.domain.mission.service.command.MissionCommandService;
import com.example.umc9th.domain.mission.service.query.MissionQueryService;
import com.example.umc9th.global.annotation.ExistStore;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
public class MissionController implements MissionControllerDocs{
    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

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



    // 가게의 미션 목록
    @Override
    @GetMapping("/missions")
    public ApiResponse<MissionResponseDTO.MissionPreViewListDTO> findMission(
            @RequestParam String storeName,
            @RequestParam Integer page
    ){
        return ApiResponse.onSuccess(MissionSuccessCode.FOUND, missionQueryService.findMission(storeName, page));
    }

    // 내가 진행중인 미션 목록
    @Override
    @GetMapping("/missions/my/in-progress")
    public ApiResponse<MemberMissionResponseDTO.MemberMissionPreViewListDTO> findMyMissionInProgress(
            @RequestParam(defaultValue = "1") Integer page
    ){
        return ApiResponse.onSuccess(MissionSuccessCode.FOUND, missionQueryService.findMyMission(false, page));
    }

    // 내가 진행중인 미션 목록
    @GetMapping("/missions/my/completed")
    @Override
    public ApiResponse<MemberMissionResponseDTO.MemberMissionPreViewListDTO> findMyMissionCompleted(
            @RequestParam(defaultValue = "1") Integer page
    ){
        return ApiResponse.onSuccess(MissionSuccessCode.FOUND, missionQueryService.findMyMission(true, page));
    }

    // 진행중인 미션 진행 완료로 바꾸기 (변경하고, 변경된 상태의 미션을 조회까지 해야 함)
    @PostMapping("/missions/complete")
    @Override
    public ApiResponse<MemberMissionResponseDTO.MemberMissionPreViewDTO> completeMission(
            @RequestParam Long missionId) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionCommandService.completeMission(missionId)
        );
    }

}
