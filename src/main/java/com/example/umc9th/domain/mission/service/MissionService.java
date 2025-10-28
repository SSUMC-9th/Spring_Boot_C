package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.dto.MissionResponseDto;
import com.example.umc9th.domain.mission.entity.enums.EStatus;
import com.example.umc9th.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MissionService {
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    // 진행 중, 진행 완료인 미션 조회
    public Page<MissionResponseDto> getMyMissions(Long memberId, int page, int size) {

        Pageable pageable = PageRequest.of(page, size); // 원하는 사이즈로 페이징

        Member member = memberRepository.findById(Math.toIntExact(memberId)).orElseThrow();

        List<EStatus> statuses = List.of(EStatus.IN_PROGRESS, EStatus.COMPLETED);

        Page<MissionResponseDto> missionPage = memberMissionRepository.findMissionsByStatusIn(
                member, statuses, pageable
        );

        return missionPage;
    }

    // 선택된 지역의 진행 전 미션 조회
    public Page<MissionResponseDto> getMissionsByRegion(Long memberId, String selectedRegion, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);  // 원하는 사이즈로 페이징

        Member member = memberRepository.findById(Math.toIntExact(memberId))    // memberId로 해당 유저의 엔티티 반환 (못찾을 시 예외 throw)
                .orElseThrow(() -> new IllegalArgumentException("유저 없음"));

        Page<MissionResponseDto> missionPagebyRegion = memberMissionRepository.findMissionsBySelectedRegion(    // 매개변수
                member,
                EStatus.TODO,
                selectedRegion,
                pageable
        );
        return missionPagebyRegion;
    }
}
