package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.request.MissionRequestDto;
import com.example.umc9th.domain.mission.dto.response.MissionResponseDto;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc9th.domain.mission.service.command.MissionCommandService;
import com.example.umc9th.domain.mission.service.query.MissionQueryService;
import com.example.umc9th.global.annotation.CheckPage;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController implements MissionControllerDocs {

    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    // 미션 도전하기 API
    @PostMapping("/challenge")
    public ApiResponse<MissionResponseDto.JoinResultDTO> challengeMission(
            @RequestBody @Valid MissionRequestDto request
    ) {

        Long memberId = 1L; // 현재 DB에 등록된 member가 없으므로 1로 가정 -> 추후 token으로 memberId 받아오기

        MemberMission memberMission = missionCommandService.challengeMission(memberId, request);

        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED,
                MissionConverter.toJoinResultDTO(memberMission));
    }

    // 가게별 미션 목록 조회 API
    @GetMapping("/store/{storeId}")
    @Override
    public ApiResponse<MissionResponseDto.MissionPreViewListDTO> getMissionListByStore(
            @PathVariable Long storeId,
            @CheckPage Integer page
    ){
        MissionResponseDto.MissionPreViewListDTO missionList = missionQueryService.getMissionListByStore(storeId, page);
        MissionSuccessCode code = MissionSuccessCode.MISSION_FOUND;
        return ApiResponse.onSuccess(code, missionList);
    }

    // 내가 도전 중인 미션 목록 조회 API
    @GetMapping("/my/in_progress")
    @Override
    public ApiResponse<MissionResponseDto.MissionPreViewListDTO> getMyChallengingMissions(
            @RequestHeader(name = "Authorization", required = false) String authorizationHeader,
            @CheckPage Integer page
    ) {
        Long memberId = 1L; // 임시 ID

        MissionResponseDto.MissionPreViewListDTO result =
                missionQueryService.getMyMissionList(memberId, page);
        MissionSuccessCode code = MissionSuccessCode.MISSION_FOUND;

        return ApiResponse.onSuccess(code, result);
    }
}