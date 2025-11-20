package com.springboot.umc9th.domain.store.dto.req;

import lombok.Builder;


public class StoreReqDTO {
   @Builder
    public record CreateDTO (
       String storeName,
        String storeAddress,
        String storeNumber){}
}

