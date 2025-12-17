package com.springboot.umc9th.domain.member.converter;

import com.springboot.umc9th.domain.member.dto.req.MemberReqDTO;
import com.springboot.umc9th.domain.member.dto.res.MemberResDTO;
import com.springboot.umc9th.domain.member.entity.Member;
import com.springboot.umc9th.domain.member.enums.Role;

import java.awt.*;

public class MemberConverter {

    // Entity -> DTO
    public static MemberResDTO.JoinDTO toJoinDTO(
            Member member
    ){
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getId())
                .createAt(member.getCreatedAt())
                .build();
    }

    // DTO -> Entity
    public static Member toMember(
            MemberReqDTO.JoinDTO dto,
            String password,
            Role role
            ){
        return Member.builder()
                .name(dto.name())
                .birthday(dto.birth())
                .address(dto.address())
                .gender(dto.gender())
                .email(dto.email())
                .password(password)
                .role(role)
                .build();
    }

    public static MemberResDTO.LoginDTO toLoginDTO(
            Member member,
            String accessToken
    ) {
        return MemberResDTO.LoginDTO.builder()
                .memberId(member.getId())
                .accessToken(accessToken)
                .build();
    }
}