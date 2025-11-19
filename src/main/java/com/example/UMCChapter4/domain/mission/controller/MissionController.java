package com.example.UMCChapter4.domain.mission.controller;

import com.example.UMCChapter4.domain.mission.dto.mission.MissionReqDTO;
import com.example.UMCChapter4.domain.mission.dto.mission.MissionResDTO;
import com.example.UMCChapter4.domain.mission.exception.code.MissionSuccessCode;
import com.example.UMCChapter4.domain.mission.repository.MissionRepository;
import com.example.UMCChapter4.domain.mission.service.command.MissionCommandService;
import com.example.UMCChapter4.domain.mission.service.query.MissionQueryService;
import com.example.UMCChapter4.global.apiPayload.ApiResponse;
import com.example.UMCChapter4.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {
    public final MissionRepository missionRepository;
    public final MissionCommandService missionCommandService;
    public final MissionQueryService missionQueryService;

    @PostMapping("/create")
    public ApiResponse<MissionResDTO.MissionCreateDTO> createMission(
            @RequestBody MissionReqDTO.MissionCreateDTO ReqDTO
    ){
        MissionResDTO.MissionCreateDTO ResDTO= missionCommandService.createMission(ReqDTO);

        return ApiResponse.onSuccess(
                MissionSuccessCode.CREATED,
                ResDTO
        );
    }
}
