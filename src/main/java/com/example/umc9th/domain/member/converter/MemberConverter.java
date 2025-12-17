package com.example.umc9th.domain.member.converter;

import com.example.umc9th.domain.member.dto.request.MemberRequestDTO;
import com.example.umc9th.domain.member.dto.response.MemberResponseDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.global.auth.enums.Role;

public class MemberConverter {
    // Entity -> DTO
    public static MemberResponseDTO.JoinDTO toJoinDTO(Member member) {
        return MemberResponseDTO.JoinDTO.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    // Entity -> DTO
    public static MemberResponseDTO.LoginDTO toLoginDTO(Member member, String accessToken) {
        return MemberResponseDTO.LoginDTO.builder()
                .memberId(member.getId())
                .accessToken(accessToken)
                .build();
    }

    // DTO -> Entity
    public static Member toMember(MemberRequestDTO.JoinDTO dto, String password, Role role) {
        return Member.builder()
                .name(dto.name())
                .email(dto.email())
                .password(password)
                .role(role)
                .birth(dto.birth())
                .address(dto.address())
                .gender(dto.gender())
                .build();
    }
}
