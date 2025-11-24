package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.request.MissionRequestDto;
import com.example.umc9th.domain.mission.dto.response.MissionResponseDto;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.service.command.MissionCommandService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionCommandService missionCommandService;

    @PostMapping("/challenge")
    public ApiResponse<MissionResponseDto.JoinResultDTO> challengeMission(
            @RequestBody @Valid MissionRequestDto request
    ) {

        Long memberId = 1L; // 현재 DB에 등록된 member가 없으므로 1로 가정 -> 추후 token으로 memberId 받아오기

        MemberMission memberMission = missionCommandService.challengeMission(memberId, request);

        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED,
                MissionConverter.toJoinResultDTO(memberMission));
    }
}