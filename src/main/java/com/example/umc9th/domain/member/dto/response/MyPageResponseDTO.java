package com.example.umc9th.domain.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class MyPageResponseDTO {
    private Long memberId;
    private String name;
    private String email;
    private String phone;
    private int point;
}
