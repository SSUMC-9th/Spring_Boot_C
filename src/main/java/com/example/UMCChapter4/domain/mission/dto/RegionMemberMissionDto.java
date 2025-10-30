package com.example.UMCChapter4.domain.mission.dto;

import java.time.LocalDate;

public record RegionMemberMissionDto(
        String storeName,
        String description,
        int points,
        LocalDate deadline
){}
