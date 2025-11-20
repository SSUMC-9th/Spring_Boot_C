package com.example.UMCChapter4.domain.store.dto;

import com.example.UMCChapter4.domain.member.enums.Address;
import lombok.Builder;

public class StoreReqDTO {

    @Builder
    public record StoreCreateDTO(
            String name,
            Long managerNumber,
            Address address,
            String detail_address,
            Long locationId
    ){}
}
