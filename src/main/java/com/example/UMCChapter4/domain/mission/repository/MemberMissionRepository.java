package com.example.UMCChapter4.domain.mission.repository;

import com.example.UMCChapter4.domain.member.entity.Member;
import com.example.UMCChapter4.domain.mission.dto.MyMissionStatusDto;
import com.example.UMCChapter4.domain.mission.dto.LocationMemberMissionDto;
import com.example.UMCChapter4.domain.mission.entity.MemberMission;
import com.example.UMCChapter4.domain.mission.enums.EStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    // 진행 중 or 진행완료 멤버미션 목록
    @Query(value = """
    select new com.example.UMCChapter4.domain.mission.dto.MyMissionStatusDto(
      mm.id, m.points, s.name, m.condition
    )
    from MemberMission mm
    join mm.mission m
    join m.store s
    where mm.member.id = :memberId
      and mm.status = :status
      and (m.deadline >= :today)
    order by m.deadline asc, mm.id desc
    """)
    Page<MyMissionStatusDto> findMyMissions(
            @Param("memberId") Long memberId,
            @Param("today") LocalDate today,
            @Param("status") EStatus status,
            Pageable pageable
    );


    //홈화면 지역 내 유저미션(진행 중) 목록
    @Query("""
    select new com.example.UMCChapter4.domain.mission.dto.LocationMemberMissionDto(
      s.name, m.condition, m.points, m.deadline
    )
    from MemberMission mm
    join mm.mission m
    join m.store s
    where mm.member.id = :userId
      and s.location.id = :locationId
      and mm.status = com.example.UMCChapter4.domain.mission.enums.EStatus.PROGRESS
    order by m.deadline asc, mm.id asc
    """)
    Page<LocationMemberMissionDto> findMyLocationMissionsInProgress(
            @Param("memberId") Long memberId,
            @Param("locationId") Long locationId,
            Pageable pageable
    );

    // 홈화면 지역내 멤버 미션 완료 수
    @Query("""
    select count(mm.id)
    from MemberMission mm
    join mm.mission m
    join m.store s
    where mm.member.id = :memberId
      and mm.status = com.example.UMCChapter4.domain.mission.enums.EStatus.COMPLETED
      and s.location.id = :locationId
    """)
    long countMyLocationCompleted(
            @Param("memberId") Long memberId,
            @Param("locationId") Long locationId
    );


    @Query(value = "SELECT mm FROM MemberMission mm " +
            "JOIN FETCH mm.mission m " +
            "JOIN FETCH m.store s " +
            "WHERE mm.member = :member AND mm.status = :eStatus",
            countQuery = "SELECT COUNT(mm) FROM MemberMission mm WHERE mm.member = :member AND mm.status = :eStatus")
    Page<MemberMission> findAllByMemberAndStatus(
            @Param("member") Member member,
            @Param("eStatus") EStatus eStatus,
            Pageable pageable
    );
}
