package com.example.UMCChapter4.domain.member.converter;

import com.example.UMCChapter4.domain.member.dto.MemberReqDTO;
import com.example.UMCChapter4.domain.member.dto.MemberResDTO;
import com.example.UMCChapter4.domain.member.entity.Member;
import com.example.UMCChapter4.domain.member.enums.Role;

public class MemberConverter {

    // Entity -> DTO
    public static MemberResDTO.MemberJoinDTO toJoinDTO(
            Member member
    ){
        return MemberResDTO.MemberJoinDTO.builder()
                .memberId(member.getId())
                .createAt(member.getCreatedAt())
                .build();
    }

    // DTO -> Entity
    public static Member toMember(
            MemberReqDTO.MemberSignUpDTO dto,
            String password,
            Role role
    ){
        return Member.builder()
                .name(dto.name())
                .email(dto.email()) // 추가된 코드
                .password(password) // 추가된 코드
                .role(role)
                .birth(dto.birth())
                .address(dto.address())
                .detailAddress(dto.specAddress())
                .gender(dto.gender())
                .build();
    }

    public static MemberResDTO.MemberLoginDTO toLoginDTO(Long memberId, String accessToken) {
        return MemberResDTO.MemberLoginDTO.builder()
                .memberId(memberId)
                .accessToken(accessToken)
                .build();
    }

    public static MemberResDTO.MemberLoginDTO toLoginDTO(Long memberId) {
        return MemberResDTO.MemberLoginDTO.builder()
                .memberId(memberId)
                .build();
    }

    public static MemberResDTO.MemberLogoutDTO toLogoutDTO(String sessionId) {
        return MemberResDTO.MemberLogoutDTO.builder()
                .sessionId(sessionId)
                .build();
    }
}