package com.example.umc9th.domain.test.converter;

import com.example.umc9th.domain.test.dto.response.TestResponseDTO;

public class TestConverter {
    // 객체 -> DTO
    public static TestResponseDTO.Testing toTestingDTO(String testing) {
        return TestResponseDTO.Testing.builder()
                .testString(testing)
                .build();
    }

    // 객체 -> DTO
    public static TestResponseDTO.Exception toExceptionDTO(String testing){
        return TestResponseDTO.Exception.builder()
                .testString(testing)
                .build();
    }
}
