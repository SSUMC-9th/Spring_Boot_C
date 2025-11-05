package com.example.UMCChapter4.domain.mission.dto;

import java.time.LocalDate;

public record LocationMemberMissionDto(
        String storeName,
        String description,
        Integer points,
        LocalDate deadline
){}
