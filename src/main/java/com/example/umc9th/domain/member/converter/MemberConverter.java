package com.example.umc9th.domain.member.converter;

import com.example.umc9th.domain.member.dto.request.MemberRequestDTO;
import com.example.umc9th.domain.member.dto.response.MemberResponseDTO;
import com.example.umc9th.domain.member.entity.Member;

public class MemberConverter {
    // Entity -> DTO
    public static MemberResponseDTO.JoinDTO toJoinDTO(Member member) {
        return MemberResponseDTO.JoinDTO.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    // DTO -> Entity
    public static Member toMember(MemberRequestDTO.JoinDTO dto) {
        return Member.builder()
                .name(dto.name())
                .birth(dto.birth())
                .address(dto.address())
                .gender(dto.gender())
                .build();
    }
}
