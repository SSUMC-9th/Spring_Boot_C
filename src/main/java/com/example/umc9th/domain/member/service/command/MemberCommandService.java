package com.example.umc9th.domain.member.service.command;

import com.example.umc9th.domain.member.dto.request.MemberRequestDto;
import com.example.umc9th.domain.member.dto.response.MemberResponseDto;

public interface MemberCommandService {

    MemberResponseDto.JoinDTO signup(
            MemberRequestDto.JoinDTO dto
    );

}
