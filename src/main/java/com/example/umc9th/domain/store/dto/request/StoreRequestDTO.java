package com.example.umc9th.domain.store.dto.request;

import com.example.umc9th.global.annotation.ExistRegion;
import jakarta.validation.constraints.NotBlank;

public class StoreRequestDTO {
    public record joinDTO(
            @ExistRegion
            Long regionId,
            @NotBlank(message = "가게 이름은 필수 입력입니다.")
            String name,
            @NotBlank(message = "주소는 필수 입력입니다.")
            String address
    ){}
}
