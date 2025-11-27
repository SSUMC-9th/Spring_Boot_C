package com.example.umc9th.domain.mission.service.command;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.converter.MemberMissionConverter;
import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.request.MissionRequestDTO;
import com.example.umc9th.domain.mission.dto.response.MemberMissionResponseDTO;
import com.example.umc9th.domain.mission.dto.response.MissionResponseDTO;
import com.example.umc9th.domain.mission.dto.response.MyMissionResponseDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc9th.domain.mission.repository.MemberMissionRepository;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public MissionResponseDTO.createMission createMission(Long storeId, MissionRequestDTO.createMission missionRequestDTO) {

        Mission mission = MissionConverter.toEntity(storeId, missionRequestDTO);
        mission=missionRepository.save(mission);
        return MissionConverter.toDTO(mission);
    }

    @Override
    @Transactional
    public MissionResponseDTO.memberMissionDTO challengeMission(Long memberId, Long missionId) {
        Member member = memberRepository.findById(memberId).orElseThrow(()->new GeneralException(MemberErrorCode.MEMBER_NOT_FOUND));
        Mission mission = missionRepository.findById(missionId).orElseThrow(()->new GeneralException(MissionErrorCode.MISSION_NOT_FOUND));

        // 이미 도전 중이거나 완료한 미션인지 확인
        // 이미 진행 중/완료한 미션인지 확인
        if (memberMissionRepository.existsByMemberAndMission(member, mission)) {
            throw new GeneralException(MissionErrorCode.MISSION_ALREADY_CHALLENGED);
        }
        // 새로 도전하기 생성
        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(false) // 도전 시작 → 아직 완료 아님
                .build();

        memberMissionRepository.save(memberMission);
        return MissionConverter.toMemberMissionDTO(memberMission);
    }

    @Override
    @Transactional
    public MemberMissionResponseDTO.MemberMissionPreViewDTO completeMission(Long missionId) {
        Long memberId=1L;
        Member member = memberRepository.findById(memberId).orElseThrow(()->new GeneralException(MemberErrorCode.MEMBER_NOT_FOUND));
        MemberMission memberMission = memberMissionRepository.findByMemberIdAndMissionId(memberId, missionId)
                .orElseThrow(() -> new GeneralException(MissionErrorCode.MISSION_NOT_FOUND));

        // 이미 완료된 미션이면 예외
        if (memberMission.isStatus()) {
            throw new GeneralException(MissionErrorCode.MISSION_COMPLETED_ALREADY);
        }

        memberMission.setStatus(true);

        return MemberMissionConverter.toMissionPreViewDTO(memberMission);
    }
}
