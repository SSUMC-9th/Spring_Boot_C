package com.example.UMCChapter4.domain.mission.controller;

import com.example.UMCChapter4.domain.mission.dto.mission.MissionReqDTO;
import com.example.UMCChapter4.domain.mission.dto.mission.MissionResDTO;
import com.example.UMCChapter4.domain.mission.exception.code.MissionSuccessCode;
import com.example.UMCChapter4.domain.mission.repository.MissionRepository;
import com.example.UMCChapter4.domain.mission.service.command.MissionCommandService;
import com.example.UMCChapter4.domain.mission.service.query.MissionQueryService;
import com.example.UMCChapter4.global.annotation.ValidPage;
import com.example.UMCChapter4.global.apiPayload.ApiResponse;
import com.example.UMCChapter4.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "미션 API")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController implements MissionControllerDocs{
    public final MissionRepository missionRepository;
    public final MissionCommandService missionCommandService;
    public final MissionQueryService missionQueryService;

    @PostMapping("/create")
    public ApiResponse<MissionResDTO.MissionCreateDTO> createMission(
            @RequestBody MissionReqDTO.MissionCreateDTO ReqDTO
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.CREATED,
                missionCommandService.createMission(ReqDTO)
        );
    }

    @GetMapping("/store/get")
    public ApiResponse<MissionResDTO.MissionPreviewListDTO> getMission(
            @RequestParam String storeName,
            @RequestParam(defaultValue = "1") @ValidPage Integer pageNumber
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.OK,
                missionQueryService.getMission(storeName, pageNumber-1)
        );
    }
}
