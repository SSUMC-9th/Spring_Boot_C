package com.example.UMCChapter4.domain.mission.dto;

public record MyMissionStatusDto(
        Long memberMissionId,
        Integer points,
        String storeName,
        String description
) {}
