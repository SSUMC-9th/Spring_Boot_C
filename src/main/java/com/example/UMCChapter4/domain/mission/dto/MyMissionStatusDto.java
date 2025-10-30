package com.example.UMCChapter4.domain.mission.dto;

public record MyMissionStatusDto(
        Long memberMissionId,
        int points,
        String storeName,
        String description
) {}
