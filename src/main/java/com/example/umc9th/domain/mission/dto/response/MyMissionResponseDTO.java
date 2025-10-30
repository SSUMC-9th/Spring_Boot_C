package com.example.umc9th.domain.mission.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyMissionResponseDTO {
    private Long memberMissionId; //pk
    private int point;
    private String storeName;
    private String missionContent;
    private boolean status;
}
