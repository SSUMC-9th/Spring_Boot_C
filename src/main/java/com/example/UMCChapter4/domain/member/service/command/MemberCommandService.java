package com.example.UMCChapter4.domain.member.service.command;

import com.example.UMCChapter4.domain.member.dto.MemberReqDTO;
import com.example.UMCChapter4.domain.member.dto.MemberResDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

public interface MemberCommandService {
    //회원가입
    MemberResDTO.MemberJoinDTO signUp(
            MemberReqDTO.MemberSignUpDTO dto
    );

    MemberResDTO.MemberLoginDTO login(
            MemberReqDTO.MemberLoginDTO dto,
            HttpServletRequest request,
            String type
    );

    MemberResDTO.MemberLoginDTO tokenLogin(
            MemberReqDTO.MemberLoginDTO dto
    );

    MemberResDTO.MemberLoginDTO sessionLogin(
            MemberReqDTO.MemberLoginDTO dto,
            HttpServletRequest request
    );

    MemberResDTO.MemberLogoutDTO sessionLogout(
            HttpServletRequest request,
            HttpServletResponse response
    );
}
