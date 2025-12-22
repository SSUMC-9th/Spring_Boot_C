package com.example.umc9th.domain.member.service.query;

import com.example.umc9th.domain.member.dto.request.MemberRequestDto;
import com.example.umc9th.domain.member.dto.response.MemberResponseDto;
import jakarta.validation.Valid;

public interface MemberQueryService {
    MemberResponseDto.LoginDTO login(MemberRequestDto.@Valid LoginDTO dto);
}
