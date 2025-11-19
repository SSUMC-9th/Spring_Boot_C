package com.springboot.umc9th.domain.mission.service;

import com.springboot.umc9th.domain.member.entity.Member;
import com.springboot.umc9th.domain.member.repository.MemberRepository;
import com.springboot.umc9th.domain.mission.converter.MissionChallengeConverter;
import com.springboot.umc9th.domain.mission.dto.res.MissionChallengeResDTO;
import com.springboot.umc9th.domain.mission.entity.Mission;
import com.springboot.umc9th.domain.mission.entity.mapping.UserMission;
import com.springboot.umc9th.domain.mission.repository.MissionRepository;
import com.springboot.umc9th.domain.mission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;

    private static final Long FIXED_MEMBER_ID = 1L;

    @Transactional
    @Override
    public MissionChallengeResDTO.ChallengeResDTO challengeMission(Long missionId) {

        // 1) 하드코딩 멤버 가져오기
        Member member = memberRepository.findById(FIXED_MEMBER_ID)
                .orElseThrow(() -> new RuntimeException("member not found"));

        // 2) 미션 조회
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new RuntimeException("mission not found"));

        // 3) UserMission 생성
        UserMission userMission = UserMission.builder()
                .member(member)
                .mission(mission)
                .build();

        userMissionRepository.save(userMission);

        return MissionChallengeConverter.toChallengeResDTO(userMission);
    }
}
