package com.springboot.umc9th.domain.mission.converter;

import com.springboot.umc9th.domain.mission.dto.res.MissionResDTO;
import com.springboot.umc9th.domain.mission.entity.Mission;
import com.springboot.umc9th.domain.mission.entity.mapping.UserMission;
import org.springframework.data.domain.Page;

import java.util.List;

public class MissionConverter {

    public static MissionResDTO.MissionDTO toMissionDTO(Mission mission) {
        return MissionResDTO.MissionDTO.builder()
                .missionId(mission.getId())
                .point(mission.getPoint())
                .missionSpec(mission.getMission_condition())
                // LocalDateTime -> LocalDate로 변환
                .deadline(mission.getDeadline().toLocalDate())
                .build();
    }

    public static MissionResDTO.MissionPreViewListDTO toMissionPreViewListDTO(Page<Mission> missionPage) {
        List<MissionResDTO.MissionDTO> missionDTOList = missionPage.stream()
                .map(MissionConverter::toMissionDTO)
                .toList();

        return MissionResDTO.MissionPreViewListDTO.builder()
                .isLast(missionPage.isLast())
                .isFirst(missionPage.isFirst())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .listSize(missionDTOList.size())
                .missionList(missionDTOList)
                .build();
    }

    public static MissionResDTO.MyMissionDTO toMyMissionDTO(UserMission userMission) {
        return MissionResDTO.MyMissionDTO.builder()
                .missionId(userMission.getMission().getId())
                .storeName(userMission.getMission().getStore().getStoreName()) // Mission -> Store -> Name
                .point(userMission.getMission().getPoint())
                .missionSpec(userMission.getMission().getMission_condition())
                .deadline(userMission.getMission().getDeadline().toLocalDate()) // Mission -> Deadline
                .build();
    }

    public static MissionResDTO.MyMissionPreViewListDTO toMyMissionPreViewListDTO(Page<UserMission> userMissionPage) {
        List<MissionResDTO.MyMissionDTO> myMissionDTOList = userMissionPage.stream()
                .map(MissionConverter::toMyMissionDTO)
                .toList();

        return MissionResDTO.MyMissionPreViewListDTO.builder()
                .isLast(userMissionPage.isLast())
                .isFirst(userMissionPage.isFirst())
                .totalPage(userMissionPage.getTotalPages())
                .totalElements(userMissionPage.getTotalElements())
                .listSize(myMissionDTOList.size())
                .missionList(myMissionDTOList)
                .build();
    }
}
