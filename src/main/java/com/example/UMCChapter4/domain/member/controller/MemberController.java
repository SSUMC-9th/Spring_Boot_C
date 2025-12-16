package com.example.UMCChapter4.domain.member.controller;

import com.example.UMCChapter4.domain.member.dto.MemberReqDTO;
import com.example.UMCChapter4.domain.member.dto.MemberResDTO;
import com.example.UMCChapter4.domain.member.exception.code.MemberErrorCode;
import com.example.UMCChapter4.domain.member.exception.code.MemberSuccessCode;
import com.example.UMCChapter4.domain.member.service.command.MemberCommandService;
import com.example.UMCChapter4.global.apiPayload.ApiResponse;
import com.example.UMCChapter4.global.apiPayload.code.GeneralErrorCode;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "멤버 API")
@Validated
@RestController
@RequiredArgsConstructor
public class MemberController implements MemberControllerDocs {

    private final MemberCommandService memberCommandService;

    // 회원가입
    @PostMapping("/auth/sign-up")
    public ApiResponse<MemberResDTO.MemberJoinDTO> signUp(
            @RequestBody @Valid MemberReqDTO.MemberSignUpDTO dto
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.CREATED,
                memberCommandService.signUp(dto)
        );
    }

    // 로그인
    @PostMapping("/auth/login")
    public ApiResponse<MemberResDTO.MemberLoginDTO> login(
            @RequestBody @Valid MemberReqDTO.MemberLoginDTO dto,
            HttpServletRequest request,
            @RequestParam String type
    ){
        return ApiResponse.onSuccess(
                MemberSuccessCode.FOUND,
                memberCommandService.login(dto, request, type)
        );
    }

    // 로그아웃(session)
    @PostMapping("/auth/logout")
    public ApiResponse<MemberResDTO.MemberLogoutDTO> logout(
            HttpServletRequest request,
            HttpServletResponse response
    ){
        return ApiResponse.onSuccess(
                MemberSuccessCode.OK,
                memberCommandService.sessionLogout(request, response)
        );
    }
}
