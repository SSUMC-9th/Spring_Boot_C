package com.example.umc9th.domain.member.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class MypageResponseDto {
    private Long id;
    private String name;
    private String email;
    private String phone_num;
    private Long point;
}
