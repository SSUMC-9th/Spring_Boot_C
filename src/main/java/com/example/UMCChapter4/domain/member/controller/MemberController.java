package com.example.UMCChapter4.domain.member.controller;

import com.example.UMCChapter4.domain.member.dto.MemberReqDTO;
import com.example.UMCChapter4.domain.member.dto.MemberResDTO;
import com.example.UMCChapter4.domain.member.exception.code.MemberSuccessCode;
import com.example.UMCChapter4.domain.member.service.command.MemberCommandService;
import com.example.UMCChapter4.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "멤버 API")
@RestController
@RequiredArgsConstructor
public class MemberController implements MemberControllerDocs {

    private final MemberCommandService memberCommandService;

    // 회원가입
    @PostMapping("/auth/sign-up")
    public ApiResponse<MemberResDTO.MemberJoinDTO> signUp(
            @RequestBody MemberReqDTO.MemberJoinDTO dto
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.CREATED, memberCommandService.signUp(dto));
    }
}
