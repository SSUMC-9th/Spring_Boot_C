package com.example.UMCChapter4.domain.store.dto;

import lombok.Builder;

public class StoreResDTO {

    @Builder
    public record StoreCreateDTO(
            Long storeId
    ){}
}
