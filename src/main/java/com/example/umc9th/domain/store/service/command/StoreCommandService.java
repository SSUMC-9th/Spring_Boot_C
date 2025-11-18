package com.example.umc9th.domain.store.service.command;

import com.example.umc9th.domain.store.dto.request.StoreRequestDTO;
import com.example.umc9th.domain.store.dto.response.StoreResponseDTO;

public interface StoreCommandService {
    public StoreResponseDTO.JoinDTO createStore(StoreRequestDTO.joinDTO requestDTO);

}
