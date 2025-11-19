package com.example.UMCChapter4.domain.mission.converter;

import com.example.UMCChapter4.domain.member.entity.Member;
import com.example.UMCChapter4.domain.mission.dto.membermission.MemberMissionReqDTO;
import com.example.UMCChapter4.domain.mission.dto.membermission.MemberMissionResDTO;
import com.example.UMCChapter4.domain.mission.entity.MemberMission;
import com.example.UMCChapter4.domain.mission.entity.Mission;
import com.example.UMCChapter4.domain.mission.enums.EStatus;

public class MemberMissionConverter {

    public static MemberMission toMemberMission(
            MemberMissionReqDTO.MemberMissionInProgressDTO ReqDTO,
            Mission mission,
            Member member
    ) {
        return MemberMission.builder()
                .status(EStatus.PROGRESS)
                .mission(mission)
                .member(member)
                .build();
    }

    public static MemberMissionResDTO.MemberMissionInProgressDTO toInProgressDTO(
            MemberMission memberMission
    ){
        return MemberMissionResDTO.MemberMissionInProgressDTO.builder()
                .memberMissionId(memberMission.getId())
                .build();
    }
}
