package com.example.UMCChapter4.domain.member.dto;

import com.example.UMCChapter4.domain.member.enums.Address;
import com.example.UMCChapter4.domain.member.enums.Gender;
import com.example.UMCChapter4.global.annotation.ExistFoods;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    // sign-up
    @Builder
    public record MemberSignUpDTO(
            @NotBlank
            String name,
            @Email
            String email, // 추가된 속성
            @NotBlank
            String password, // 추가된 속성
            @NotNull
            Gender gender,
            @NotNull
            LocalDate birth,
            @NotNull
            Address address,
            @NotNull
            String specAddress,
            @ExistFoods
            List<Long> preferCategory
    ){}

    // login
    @Builder
    public record MemberLoginDTO(
            @Email
            String email,
            @NotBlank
            String password
    ){}
}