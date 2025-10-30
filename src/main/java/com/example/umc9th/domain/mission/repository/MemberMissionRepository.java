package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.enums.EStatus;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.dto.MissionResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // 현재 진행 중, 진행 완료한 미션 불러오기
    @Query("SELECT new com.example.umc9th.domain.mission.dto.MissionResponseDto(" +
            "mm.id, m.store.name, m.point, m.content, mm.status) " +
            "FROM MemberMission mm " +
            "JOIN mm.mission m JOIN m.store s " +
            "WHERE mm.member = :member AND mm.status IN :statuses")
    Page<MissionResponseDto> findMissionsByStatusIn(
            @Param("member") Member member,
            @Param("statuses") List<EStatus> statuses,
            Pageable pageable
    );

    // 현재 선택된 지역의 도전 가능한 미션 불러오기
    @Query("SELECT new com.example.umc9th.domain.mission.dto.MissionResponseDto(mm.id, s.name, m.point, m.content, mm.status) " +
            "FROM MemberMission mm " +
            "JOIN mm.mission m JOIN m.store s " +
            "WHERE mm.member = :member AND mm.status = :status AND s.region = :selectedRegion")
    Page<MissionResponseDto> findMissionsBySelectedRegion(
            @Param("member") Member member,
            @Param("status") EStatus status,
            @Param("selectedRegion") String selectedRegion,
            Pageable pageable
    );


}
