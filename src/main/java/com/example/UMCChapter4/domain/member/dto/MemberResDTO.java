package com.example.UMCChapter4.domain.member.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class MemberResDTO {

    @Builder
    public record MemberJoinDTO(
            Long memberId,
            LocalDateTime createAt
    ){}

    @Builder
    public record MemberLoginDTO(
            Long memberId,
            String accessToken
    ){}

    @Builder
    public record MemberLogoutDTO(
            String sessionId
    ) {}
}