package com.example.UMCChapter4.domain.member.service;

import com.example.UMCChapter4.domain.member.repository.MemberRepository;
import com.example.UMCChapter4.domain.mission.dto.RegionMemberMissionDto;
import com.example.UMCChapter4.domain.mission.repository.MemberMissionRepository;
import com.example.UMCChapter4.domain.store.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final LocationRepository locationRepository;

    // 지역 내 멤버미션 진행중 목록 (complete == false)
    public Page<RegionMemberMissionDto> getMyRegionMissions(Long memberId, Long locationId, boolean complete, Pageable pageable) {
        return memberMissionRepository.findMyLocationMissionsInProgress(memberId, locationId, pageable);
    }

    // 홈 상단 지역이름, 내 포인트 조회
    public String getRegionName(Long regionId) {
        return locationRepository.findLocationName(regionId);
    }

    public Integer getMyPoints(Long userId) {
        return memberRepository.findPointsByUserId(userId);
    }

    // 지역 내 진행완료 미션 수
    public long getMyRegionCompletedCount(Long userId, Long regionId) {
        return memberMissionRepository.countMyLocationCompleted(userId, regionId);
    }
}
