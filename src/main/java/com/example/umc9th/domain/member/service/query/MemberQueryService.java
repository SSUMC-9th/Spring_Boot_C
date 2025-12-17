package com.example.umc9th.domain.member.service.query;

import com.example.umc9th.domain.member.dto.request.MemberRequestDTO;
import com.example.umc9th.domain.member.dto.response.MemberResponseDTO;
import jakarta.validation.Valid;

public interface MemberQueryService {
    MemberResponseDTO.LoginDTO login(MemberRequestDTO.LoginDTO dto);
}
