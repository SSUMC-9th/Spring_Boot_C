package com.springboot.umc9th.domain.mission.controller;

import com.springboot.umc9th.domain.mission.dto.res.MissionChallengeResDTO;
import com.springboot.umc9th.domain.mission.exception.code.MissionSuccessCode;
import com.springboot.umc9th.domain.mission.service.MissionCommandService;
import com.springboot.umc9th.domain.mission.dto.req.MissionReqDTO;
import com.springboot.umc9th.domain.mission.dto.res.MissionResDTO;
import com.springboot.umc9th.domain.mission.service.MissionQueryService;
import com.springboot.umc9th.global.annotation.CheckPage;
import com.springboot.umc9th.global.apiPayload.ApiResponse;
import com.springboot.umc9th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
@Validated
@Tag(name = "미션 API", description = "미션 관련 api")
public class MissionController {

    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

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

    @GetMapping("/stores/{storeId}")
    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "특정 가게의 미션들을 페이징하여 조회합니다. page는 1부터 시작합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE4001", description = "페이지 번호가 유효하지 않습니다 (1 미만)."),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 1번이 1 페이지 입니다.")
    })
    public ApiResponse<MissionResDTO.MissionPreViewListDTO> getMissionList(
            @PathVariable(name = "storeId") Long storeId,
            @CheckPage @RequestParam(name = "page") Integer page
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missionQueryService.getMissionList(storeId, page));
    }


    @GetMapping("/my")
    @Operation(summary = "내가 진행 중인 미션 목록 조회 API", description = "내가 진행 중인 미션들을 페이징하여 조회합니다. page는 1부터 시작합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE4001", description = "페이지 번호가 유효하지 않습니다 (1 미만)."),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "멤버의 아이디, query string 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 1번이 1 페이지 입니다.")
    })
    public ApiResponse<MissionResDTO.MyMissionPreViewListDTO> getMyMissionList(
            @RequestParam(name = "memberId") Long memberId,
            @CheckPage @RequestParam(name = "page") Integer page
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missionQueryService.getMyMissionList(memberId, page));
    }
}

