package com.example.UMCChapter4.domain.mission.converter;

import com.example.UMCChapter4.domain.mission.dto.mission.MissionReqDTO;
import com.example.UMCChapter4.domain.mission.dto.mission.MissionResDTO;
import com.example.UMCChapter4.domain.mission.entity.Mission;
import com.example.UMCChapter4.domain.review.converter.ReviewConverter;
import com.example.UMCChapter4.domain.review.dto.ReviewResDTO;
import com.example.UMCChapter4.domain.review.entity.Review;
import com.example.UMCChapter4.domain.store.entity.Store;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.ArrayList;

public class MissionConverter {

    public static Mission toMission(MissionReqDTO.MissionCreateDTO ReqDTO, Store store){
        return Mission.builder()
                .deadline(ReqDTO.deadline())
                .condition(ReqDTO.condition())
                .points(ReqDTO.points())
                .memberMissionList(new ArrayList<>())
                .store(store)
                .build();
    }

    public static MissionResDTO.MissionCreateDTO toMissionCreateDTO(Mission mission){
        return MissionResDTO.MissionCreateDTO.builder()
                .missionId(mission.getId())
                .build();
    }

    public static MissionResDTO.MissionPreviewListDTO toMissionPreviewListDTO(
            Page<Mission> result
    ){
        return MissionResDTO.MissionPreviewListDTO.builder()
                .missionList(result.getContent().stream()
                        .map(MissionConverter::toMissionPreviewDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }



    public static MissionResDTO.MissionPreviewDTO toMissionPreviewDTO(
            Mission mission
    ){
        return MissionResDTO.MissionPreviewDTO.builder()
                .storeName(mission.getStore().getName())
                .condition(mission.getCondition())
                .points(mission.getPoints())
                .deadline(mission.getDeadline().toString())
                .createdAt(LocalDate.from(mission.getCreatedAt()))
                .build();
    }
}

