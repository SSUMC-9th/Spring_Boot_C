package com.example.UMCChapter4.domain.store.controller;

import com.example.UMCChapter4.domain.store.dto.StoreReqDTO;
import com.example.UMCChapter4.domain.store.dto.StoreResDTO;
import com.example.UMCChapter4.domain.store.exception.code.StoreSuccessCode;
import com.example.UMCChapter4.domain.store.service.StoreCommandService;
import com.example.UMCChapter4.domain.store.service.StoreQueryService;
import com.example.UMCChapter4.global.apiPayload.ApiResponse;
import com.example.UMCChapter4.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "가게 API")
@RestController
@RequiredArgsConstructor
public class StoreController implements StoreControllerDocs {

    public final StoreCommandService storeCommandService;
    public final StoreQueryService storeQueryService;

    @PostMapping("/auth/store/create")
    public ApiResponse<StoreResDTO.StoreCreateDTO> createStore(
            @RequestBody StoreReqDTO.StoreCreateDTO ReqDTO
    ){
        StoreResDTO.StoreCreateDTO ResDTO = storeCommandService.createStore(ReqDTO);

        return ApiResponse.onSuccess(
                StoreSuccessCode.CREATED,
                ResDTO
        );
    }

}
