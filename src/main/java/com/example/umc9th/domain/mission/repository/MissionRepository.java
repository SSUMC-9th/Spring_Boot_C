package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.dto.response.HomeResponseDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    // 현재 선택 된 지역에서 도전이 가능한 미션 목록
    @Query("SELECT new com.example.umc9th.domain.mission.dto.response.HomeResponseDTO(m.id, m.store.name, m.content, m.deadline) " +
            "FROM Mission m join m.store s " +
            // 현재 회원의 미션 기록 확인
            "left join MemberMission mm on m.id = mm.mission.id and mm.member.id = :memberId " +
            // 지역 필터링
            "WHERE s.region.name = :regionName ")
    public Page<HomeResponseDTO> findMissionByRegionName(
            @Param("regionName") String regionName,
            @Param("memberId") Long memberId,
            Pageable pageable);
}
