package com.springboot.umc9th.domain.mission.service;

import com.springboot.umc9th.domain.member.entity.Member;
import com.springboot.umc9th.domain.member.exception.MemberException;
import com.springboot.umc9th.domain.member.exception.code.MemberErrorCode;
import com.springboot.umc9th.domain.member.repository.MemberRepository;
import com.springboot.umc9th.domain.mission.converter.MissionChallengeConverter;
import com.springboot.umc9th.domain.mission.converter.MissionConverter;
import com.springboot.umc9th.domain.mission.dto.req.MissionReqDTO;
import com.springboot.umc9th.domain.mission.dto.res.MissionChallengeResDTO;
import com.springboot.umc9th.domain.mission.dto.res.MissionResDTO;
import com.springboot.umc9th.domain.mission.entity.Mission;
import com.springboot.umc9th.domain.mission.entity.mapping.UserMission;
import com.springboot.umc9th.domain.mission.exception.MissionException;
import com.springboot.umc9th.domain.mission.exception.code.MissionErrorCode;
import com.springboot.umc9th.domain.mission.repository.MissionRepository;
import com.springboot.umc9th.domain.mission.repository.UserMissionRepository;


import com.springboot.umc9th.domain.store.entity.Store;
import com.springboot.umc9th.domain.store.exception.StoreException;
import com.springboot.umc9th.domain.store.exception.code.StoreErrorCode;
import com.springboot.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@RequiredArgsConstructor
@Transactional
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;
    private final StoreRepository storeRepository;

    //무조건 memberId 1인 사람 가져오기
    private static final Long FIXED_MEMBER_ID = 1L;

    @Transactional
    @Override
    public MissionChallengeResDTO.ChallengeResDTO challengeMission(Long missionId) {


        Member member = memberRepository.findById(FIXED_MEMBER_ID)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));


        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));


        UserMission userMission = UserMission.builder()
                .member(member)
                .mission(mission)
                .build();

        userMissionRepository.save(userMission);

        return MissionChallengeConverter.toChallengeResDTO(userMission);
    }
    @Transactional
    @Override
    public MissionResDTO.CreateMissionResDTO createMission(Long storeId, MissionReqDTO.CreateDTO dto) {


        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));


        Mission mission = Mission.builder()
                .store(store)
                .point(dto.point())
                .deadline(dto.deadline())
                .mission_condition(dto.missionCondition())
                .build();

        Mission saved = missionRepository.save(mission);

        return MissionResDTO.CreateMissionResDTO.builder()
                .missionId(saved.getId())
                .storeId(storeId)
                .point(saved.getPoint())
                .missionCondition(saved.getMission_condition())
                .build();
    }

    @Override
    public MissionResDTO.MyMissionDTO completeMission(Long memberId, Long missionId) {


        UserMission userMission = userMissionRepository.findByMemberIdAndMissionId(memberId, missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));


        if (userMission.getComplete()) {
            throw new MissionException(MissionErrorCode.MISSION_ALREADY_SUCCESS);
        }
        userMission.setComplete(true);

        return MissionConverter.toMyMissionDTO(userMission);
    }


}
