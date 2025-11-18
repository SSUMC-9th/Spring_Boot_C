package com.example.umc9th.domain.member.service.command;

import com.example.umc9th.domain.member.dto.request.MemberRequestDTO;
import com.example.umc9th.domain.member.dto.response.MemberResponseDTO;

public interface MemberCommandService {
    public MemberResponseDTO.JoinDTO signUp(MemberRequestDTO.JoinDTO dto);
}
