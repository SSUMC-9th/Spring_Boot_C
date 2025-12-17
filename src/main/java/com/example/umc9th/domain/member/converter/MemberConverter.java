package com.example.umc9th.domain.member.converter;

import com.example.umc9th.domain.member.dto.request.MemberRequestDto;
import com.example.umc9th.domain.member.dto.response.MemberResponseDto;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.global.auth.enums.ERole;

public class MemberConverter {

    // Entity -> DTO
    public static MemberResponseDto.JoinDTO toJoinDTO(
            Member member
    ){
        return MemberResponseDto.JoinDTO.builder()
                .memberId(member.getId())
                .createAt(member.getCreatedAt())
                .build();
    }

    // DTO -> Entity
    public static Member toMember(
            MemberRequestDto.JoinDTO dto,
            String password,
            ERole role
    ){
        return Member.builder()
                .name(dto.name())
                .email(dto.email())
                .password(password)
                .role(role)
                .birthDate(dto.birth())
                .address(dto.address())
                .gender(dto.gender())
                .build();
    }
}