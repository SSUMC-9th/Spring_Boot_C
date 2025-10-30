package com.example.umc9th.domain.member.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MypageResponseDto {
    private Long id;
    private String name;
    private String email;
    private String phone_num;
    private Long point;

    public MypageResponseDto(Long id, String name, String email, String phone_num, Long point) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone_num = phone_num;
        this.point = point;
    }
}
