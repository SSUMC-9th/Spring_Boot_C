package com.springboot.umc9th.domain.member.controller;

import com.springboot.umc9th.domain.member.dto.req.MemberReqDTO;
import com.springboot.umc9th.domain.member.dto.res.MemberResDTO;
import com.springboot.umc9th.domain.member.exception.code.MemberSuccessCode;
import com.springboot.umc9th.domain.member.service.command.MemberCommandService;
import com.springboot.umc9th.domain.member.service.query.MemberQueryService;
import com.springboot.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "회원 API", description = "회원 가입 및 회원 관련 기능")
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;
    // 회원가입
    @PostMapping("/sign-up")
    @Operation(summary = "회원가입", description = "신규 회원을 등록")
    public ApiResponse<MemberResDTO.JoinDTO> signUp(
            @RequestBody @Valid MemberReqDTO.JoinDTO dto
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberCommandService.signup(dto));
    }

    // 로그인
    @PostMapping("/login")
    public ApiResponse<MemberResDTO.LoginDTO> login(
            @RequestBody @Valid MemberReqDTO.LoginDTO dto
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberQueryService.login(dto));
    }
}