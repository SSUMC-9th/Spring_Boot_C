package com.example.umc9th.domain.mission.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class HomeResponseDTO {
    private Long missionId;
    private String storeName;
    private String missionContent;
    private LocalDateTime deadline;


}
