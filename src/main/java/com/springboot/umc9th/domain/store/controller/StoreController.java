package com.springboot.umc9th.domain.store.controller;

import com.springboot.umc9th.domain.store.dto.req.StoreReqDTO;
import com.springboot.umc9th.domain.store.dto.res.CreateResDTO;
import com.springboot.umc9th.domain.store.exception.code.StoreSuccessCode;
import com.springboot.umc9th.domain.store.service.StoreCommandService;
import com.springboot.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
@Tag(name = "가게 API", description = "가게 등록 관련 기능")
public class StoreController {

    private final StoreCommandService storeCommandService;


    @PostMapping("/{localId}")
    @Operation(summary = "가게 등록", description = "특정 지역에 가게 추가하기 API")
    public ApiResponse<CreateResDTO.CreateStoreResDTO> createStore(
            @PathVariable Long localId,
            @RequestBody StoreReqDTO.CreateDTO dto
    ){
        return ApiResponse.onSuccess(
                StoreSuccessCode.STORE_CREATED,
                storeCommandService.createStore(localId, dto)
        );
    }

}

