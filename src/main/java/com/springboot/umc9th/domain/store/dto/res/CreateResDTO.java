package com.springboot.umc9th.domain.store.dto.res;

import lombok.Builder;

public class CreateResDTO {


    @Builder
    public record CreateStoreResDTO (
        Long storeId,
        Long localId,
        String storeName,
        String storeAddress
    ){}
}
