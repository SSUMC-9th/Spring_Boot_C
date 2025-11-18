package com.example.umc9th.domain.store.controller;

import com.example.umc9th.domain.store.dto.request.StoreRequestDTO;
import com.example.umc9th.domain.store.dto.response.StoreResponseDTO;
import com.example.umc9th.domain.store.service.command.StoreCommandService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StoreController {
    private final StoreCommandService storeCommandService;

    // 지역에 가게 추가
    @PostMapping("/stores")
    public ApiResponse<StoreResponseDTO.JoinDTO> createStore(@RequestBody @Valid StoreRequestDTO.joinDTO requestDTO) {

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, storeCommandService.createStore(requestDTO));
    }


}
