package com.example.UMCChapter4.domain.mission.controller;

import com.example.UMCChapter4.domain.mission.dto.membermission.MemberMissionReqDTO;
import com.example.UMCChapter4.domain.mission.dto.membermission.MemberMissionResDTO;
import com.example.UMCChapter4.domain.mission.exception.code.MemberMissionSuccessCode;
import com.example.UMCChapter4.domain.mission.service.command.MemberMissionCommandService;
import com.example.UMCChapter4.domain.mission.service.query.MemberMissionQueryService;
import com.example.UMCChapter4.global.annotation.ValidPage;
import com.example.UMCChapter4.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "멤버-미션")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member-missions")
public class MemberMissionController implements MemberMissionControllerDocs {
    public final MemberMissionCommandService memberMissionCommandService;
    public final MemberMissionQueryService memberMissionQueryService;

    @PostMapping("/create")
    public ApiResponse<MemberMissionResDTO.MemberMissionInProgressDTO> createMemberMission(
                @RequestBody MemberMissionReqDTO.MemberMissionCreateDTO ReqDTO
    ){
        return ApiResponse.onSuccess(
                MemberMissionSuccessCode.CREATED,
                memberMissionCommandService.createMemberMission(ReqDTO)
        );
    }

    @PostMapping("/complete")
    public ApiResponse<MemberMissionResDTO.MemberMissionCompleteDTO> completeMemberMission(
            @RequestBody MemberMissionReqDTO.MemberMissionCompleteDTO ReqDTO
    ){
        return ApiResponse.onSuccess(
                MemberMissionSuccessCode.OK,
                memberMissionCommandService.completeMemberMission(ReqDTO)
        );
    }


    @GetMapping("/status/get")
    public ApiResponse<MemberMissionResDTO.MemberMissionStatusListDTO> getStatusMemberMission(
            @RequestParam String memberName,
            @RequestParam String status,
            @RequestParam(defaultValue = "1") @ValidPage Integer pageNumber

    ) {

        return ApiResponse.onSuccess(
                MemberMissionSuccessCode.FOUND,
                memberMissionQueryService.getStatusMemberMission(status, memberName, pageNumber-1)
        );
    }
}


