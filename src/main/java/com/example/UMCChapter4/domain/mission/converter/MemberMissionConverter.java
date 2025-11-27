package com.example.UMCChapter4.domain.mission.converter;


import com.example.UMCChapter4.domain.member.entity.Member;
import com.example.UMCChapter4.domain.mission.dto.membermission.MemberMissionReqDTO;
import com.example.UMCChapter4.domain.mission.dto.membermission.MemberMissionResDTO;
import com.example.UMCChapter4.domain.mission.entity.MemberMission;
import com.example.UMCChapter4.domain.mission.entity.Mission;
import com.example.UMCChapter4.domain.mission.enums.EStatus;
import com.example.UMCChapter4.domain.store.entity.Store;
import org.springframework.data.domain.Page;

public class MemberMissionConverter {

    public static MemberMission toMemberMission(
            MemberMissionReqDTO.MemberMissionCreateDTO ReqDTO,
            Mission mission,
            Member member
    ) {
        return MemberMission.builder()
                .status(EStatus.PROGRESS)
                .mission(mission)
                .member(member)
                .build();
    }

    public static MemberMissionResDTO.MemberMissionInProgressDTO toMemberMissionInProgressDTO(
            MemberMission memberMission
    ){
        return MemberMissionResDTO.MemberMissionInProgressDTO.builder()
                .memberMissionId(memberMission.getId())
                .build();
    }

    public static MemberMissionResDTO.MemberMissionCompleteDTO toMemberMissionCreateDTO(
            MemberMission memberMission,
            Store store,
            MemberMissionResDTO.MemberMissionStatusListDTO inProgressList
    ){
        return MemberMissionResDTO.MemberMissionCompleteDTO.builder()
                .storeName(store.getName())
                .points(memberMission.getMission().getPoints())
                .description(memberMission.getMission().getCondition())
                .status(memberMission.getStatus().toString())
                .inProgressList(inProgressList)
                .build();


    }

    public static MemberMissionResDTO.MemberMissionStatusListDTO toMemberMissionStatusListDto(
            Page<MemberMission> result
    ) {
        return MemberMissionResDTO.MemberMissionStatusListDTO.builder()
                .memberMissionList(result.getContent().stream()
                        .map(MemberMissionConverter::toMemberMissionStatusDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static MemberMissionResDTO.MemberMissionStatusDTO toMemberMissionStatusDTO(
            MemberMission memberMission
    ){
        return MemberMissionResDTO.MemberMissionStatusDTO.builder()
                .storeName(memberMission.getMission().getStore().getName())
                .condition(memberMission.getMission().getCondition())
                .points(memberMission.getMission().getPoints())
                .status(memberMission.getStatus().toString())
                .build();
    }
}
//
//String storeName,
//String description,
//Integer points,
//String status,
//LocalDate createdAt
