package com.example.umc9th.domain.member.controller;

import com.example.umc9th.domain.member.dto.request.MemberRequestDTO;
import com.example.umc9th.domain.member.dto.response.MemberResponseDTO;
import com.example.umc9th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc9th.domain.member.service.command.MemberCommandService;
import com.example.umc9th.domain.member.service.query.MemberQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;
    // 회원가입
    @PostMapping("/sign-up")
    public ApiResponse<MemberResponseDTO.JoinDTO> signUp(@RequestBody @Valid MemberRequestDTO.JoinDTO dto) {
        return ApiResponse.onSuccess(MemberSuccessCode.CREATE, memberCommandService.signUp(dto));
    }

    // 로그인
    @PostMapping("/login")
    public ApiResponse<MemberResponseDTO.LoginDTO> login(@RequestBody @Valid MemberRequestDTO.LoginDTO dto) {
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberQueryService.login(dto));
    }
}
