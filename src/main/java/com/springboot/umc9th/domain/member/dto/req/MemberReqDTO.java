package com.springboot.umc9th.domain.member.dto.req;

import com.springboot.umc9th.domain.member.enums.Gender;
import com.springboot.umc9th.domain.member.enums.Role;
import com.springboot.umc9th.global.annotation.ExistFoods;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record JoinDTO(
            String name,
            Gender gender,
            LocalDate birth,
            String address,
            String specAddress,
            String email,
            String password,

            @ExistFoods
            List<Long> preferCategory
    ){}

    public record LoginDTO(
            @NotBlank
            String email,
            @NotBlank
            String password
    ){}
}