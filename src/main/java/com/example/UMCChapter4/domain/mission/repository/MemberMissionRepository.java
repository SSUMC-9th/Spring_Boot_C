package com.example.UMCChapter4.domain.mission.repository;

import com.example.UMCChapter4.domain.mission.dto.MyMissionStatusDto;
import com.example.UMCChapter4.domain.mission.dto.RegionMemberMissionDto;
import com.example.UMCChapter4.domain.mission.entity.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

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
      and mm.complete = :complete
      and (m.deadline is null or m.deadline >= :today)
    order by m.deadline asc, mm.id desc
    """)
    Page<MyMissionStatusDto> findMyMissions(
            @Param("memberId") Long memberId,
            @Param("today") LocalDate today,
            @Param("complete") boolean complete,
            Pageable pageable
    );


    //홈화면 지역 내 유저미션(진행 중) 목록
    @Query("""
    select new com.example.UMCChapter4.domain.mission.dto.RegionMemberMissionDto(
      s.name, m.condition, m.points, m.deadline
    )
    from MemberMission um
    join um.mission m
    join m.store s
    where um.member.id = :userId
      and s.location.id = :regionId
      and um.complete = false
    order by m.deadline asc, um.id asc
    """)
    Page<RegionMemberMissionDto> findMyLocationMissionsInProgress(
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
      and mm.complete = true
      and s.location.id = :locationId
    """)
    long countMyLocationCompleted(@Param("memberId") Long memberId, @Param("locationId") Long locationId);


}
