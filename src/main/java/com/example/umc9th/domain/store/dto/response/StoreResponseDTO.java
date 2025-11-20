package com.example.umc9th.domain.store.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

public class StoreResponseDTO {
    @Builder
    public record JoinDTO(
            Long storeId
    ){}
}
