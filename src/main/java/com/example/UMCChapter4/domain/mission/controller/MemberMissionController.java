package com.example.UMCChapter4.domain.mission.controller;

import com.example.UMCChapter4.domain.mission.dto.membermission.MemberMissionReqDTO;
import com.example.UMCChapter4.domain.mission.dto.mission.MissionReqDTO;
import com.example.UMCChapter4.domain.mission.dto.membermission.MemberMissionResDTO;
import com.example.UMCChapter4.domain.mission.exception.code.MemberMissionSuccessCode;
import com.example.UMCChapter4.domain.mission.service.command.MemberMissionCommandService;
import com.example.UMCChapter4.domain.mission.service.query.MemberMissionQueryService;
import com.example.UMCChapter4.global.apiPayload.ApiResponse;
import com.example.UMCChapter4.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member-missions")
public class MemberMissionController {
    public final MemberMissionCommandService memberMissionCommandService;
    public final MemberMissionQueryService memberMissionQueryService;

    @PostMapping("/create")
    public ApiResponse<MemberMissionResDTO.MemberMissionInProgressDTO> createMission( // mission -> memberMission
                @RequestBody MemberMissionReqDTO.MemberMissionInProgressDTO ReqDTO
    ){
        // ReqDTO에 담긴 해당 mission 객체 기반으로 memberMission 객체 생성

        MemberMissionResDTO.MemberMissionInProgressDTO ResDTO =
                memberMissionCommandService.createMemberMission(ReqDTO);


        return ApiResponse.onSuccess(
                MemberMissionSuccessCode.CREATED,
                ResDTO
        );
    }
}


