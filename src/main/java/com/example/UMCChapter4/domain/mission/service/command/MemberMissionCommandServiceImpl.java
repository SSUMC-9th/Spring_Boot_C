package com.example.UMCChapter4.domain.mission.service.command;

import com.example.UMCChapter4.domain.member.entity.Member;
import com.example.UMCChapter4.domain.member.exception.MemberException;
import com.example.UMCChapter4.domain.member.exception.code.MemberErrorCode;
import com.example.UMCChapter4.domain.member.repository.MemberRepository;
import com.example.UMCChapter4.domain.mission.converter.MemberMissionConverter;
import com.example.UMCChapter4.domain.mission.dto.membermission.MemberMissionReqDTO;
import com.example.UMCChapter4.domain.mission.dto.membermission.MemberMissionResDTO;
import com.example.UMCChapter4.domain.mission.entity.MemberMission;
import com.example.UMCChapter4.domain.mission.entity.Mission;
import com.example.UMCChapter4.domain.mission.enums.EStatus;
import com.example.UMCChapter4.domain.mission.exception.MissionException;
import com.example.UMCChapter4.domain.mission.exception.code.MissionErrorCode;
import com.example.UMCChapter4.domain.mission.repository.MemberMissionRepository;
import com.example.UMCChapter4.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService {
    public final MemberMissionRepository memberMissionRepository;
    public final MemberRepository memberRepository;
    public final MissionRepository missionRepository;

    @Transactional
    public MemberMissionResDTO.MemberMissionInProgressDTO createMemberMission(
            MemberMissionReqDTO.MemberMissionInProgressDTO ReqDTO
    ) {

        Mission mission = missionRepository.findById(ReqDTO.missionId())
                .orElseThrow(() -> new MissionException(MissionErrorCode.NOT_FOUND));
        Member member = memberRepository.findById(1L) // memberId=1 로 하드코딩
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));


        MemberMission memberMission =
                MemberMissionConverter.toMemberMission(ReqDTO, mission, member);


        memberMissionRepository.save(memberMission);


        mission.getMemberMissionList().add(memberMission); // 연결
        member.getMemberMissionList().add(memberMission);


        return MemberMissionConverter.toInProgressDTO(memberMission);
    }
}
