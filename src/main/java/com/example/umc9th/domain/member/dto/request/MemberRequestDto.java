package com.example.umc9th.domain.member.dto.request;

import com.example.umc9th.domain.member.enums.EGender;
import com.example.umc9th.domain.member.enums.EPreferenceName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class MemberRequestDto {

    public record JoinDTO(
            @NotBlank(message = "이름은 공백일 수 없습니다.")
            String name,

            EGender gender,

            @NotNull(message = "생일은 필수 입력입니다.")
            LocalDate birth,

            @NotNull(message = "주소는 필수 입력입니다.")
            String address,

            List<EPreferenceName> preferCategory
    ){}
}
