package com.springboot.umc9th.domain.mission.controller;

import com.springboot.umc9th.domain.mission.dto.res.MissionChallengeResDTO;
import com.springboot.umc9th.domain.mission.exception.code.MissionSuccessCode;
import com.springboot.umc9th.domain.mission.service.MissionCommandService;
import com.springboot.umc9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionCommandService missionCommandService;

    @PostMapping("/{missionId}/challenge")
    public ApiResponse<MissionChallengeResDTO.ChallengeResDTO> challenge(
            @PathVariable Long missionId
    ){
        return ApiResponse.onSuccess(
                MissionSuccessCode.CHALLENGE_CREATED,
                missionCommandService.challengeMission(missionId)
        );
    }
}

