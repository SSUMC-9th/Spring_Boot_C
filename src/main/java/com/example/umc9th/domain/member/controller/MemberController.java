package com.example.umc9th.domain.member.controller;

import com.example.umc9th.domain.member.dto.request.MemberRequestDto;
import com.example.umc9th.domain.member.dto.response.MemberResponseDto;
import com.example.umc9th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc9th.domain.member.service.command.MemberCommandService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;

    // 회원가입
    @PostMapping("/sign-up")
    public ApiResponse<MemberResponseDto.JoinDTO> signUp(
            @RequestBody MemberRequestDto.JoinDTO dto
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberCommandService.signup(dto));
    }

}
