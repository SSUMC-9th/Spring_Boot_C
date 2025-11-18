package com.example.umc9th.domain.member.dto.response;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MemberResponseDTO {

    @Builder
    public record JoinDTO(
            Long memberId,
            LocalDateTime createdAt
    ){}
}
