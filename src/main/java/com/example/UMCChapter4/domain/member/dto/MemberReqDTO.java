package com.example.UMCChapter4.domain.member.dto;

import com.example.UMCChapter4.domain.member.enums.Address;
import com.example.UMCChapter4.domain.member.enums.Gender;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    @Builder
    public record MemberJoinDTO(
            String name,
            Gender gender,
            LocalDate birth,
            Address address,
            String specAddress,
            List<Long> preferCategory
    ){}
}