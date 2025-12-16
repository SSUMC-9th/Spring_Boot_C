package com.example.UMCChapter4.domain.member.controller;

import com.example.UMCChapter4.domain.member.dto.MemberReqDTO;
import com.example.UMCChapter4.domain.member.dto.MemberResDTO;
import com.example.UMCChapter4.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface MemberControllerDocs {
    @Operation(
            summary = "회원가입 API",
            description = "회원을 등록합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @PostMapping("/auth/sign-up")
    ApiResponse<MemberResDTO.MemberJoinDTO> signUp(
            @RequestBody @Valid MemberReqDTO.MemberSignUpDTO dto
    );

    @Operation(
            summary = "로그인 API",
            description = "type = \"session\", \"token\" / 두 가지의 로그인 타입을 지원합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @PostMapping("/auth/login")
    ApiResponse<MemberResDTO.MemberLoginDTO> login(
            @RequestBody @Valid MemberReqDTO.MemberLoginDTO dto,
            HttpServletRequest request,
            @RequestParam String type
    );

    @Operation(
            summary = "로그아웃 API",
            description = "세션 로그아웃을 지원합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @PostMapping("/auth/logout")
    ApiResponse<MemberResDTO.MemberLogoutDTO> logout(
            HttpServletRequest request,
            HttpServletResponse response
    );

}
