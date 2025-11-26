package com.example.UMCChapter4.domain.store.controller;

import com.example.UMCChapter4.domain.review.dto.ReviewResDTO;
import com.example.UMCChapter4.domain.store.dto.StoreReqDTO;
import com.example.UMCChapter4.domain.store.dto.StoreResDTO;
import com.example.UMCChapter4.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface StoreControllerDocs {
    @Operation(
            summary = "가게 등록 API",
            description = "가게를 등록합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @PostMapping("/auth/store/create")
    ApiResponse<StoreResDTO.StoreCreateDTO> createStore(
            @RequestBody StoreReqDTO.StoreCreateDTO ReqDTO
    );
}
