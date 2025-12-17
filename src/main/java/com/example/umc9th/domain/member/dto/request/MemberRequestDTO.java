package com.example.umc9th.domain.member.dto.request;

import com.example.umc9th.domain.member.enums.FoodName;
import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.global.annotation.ExistFoods;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class MemberRequestDTO {
    public record JoinDTO(
            @NotBlank(message = "이름은 필수 입력입니다.")
            String name,
            //@NotNull(message = "성별은 필수 입력입니다.")
            Gender gender,
            @NotNull(message = "생일은 필수 입력입니다.")
            LocalDate birth,
            @NotNull(message = "주소는 필수 입력입니다.")
            String address,
            @Email
            String email,
            @NotBlank
            String password,
            @ExistFoods
            List<Long> preferCategory
    ){}

    // 로그인
    public record LoginDTO(
            @NotBlank
            String email,
            @NotBlank
            String password
    ){}
}
