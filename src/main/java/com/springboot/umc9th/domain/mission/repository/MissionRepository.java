package com.springboot.umc9th.domain.mission.repository;

import com.springboot.umc9th.domain.mission.entity.Mission;
import com.springboot.umc9th.domain.mission.entity.mapping.UserMission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {


    @Query("SELECT um FROM UserMission um " +
            "JOIN FETCH um.mission m " +
            "JOIN FETCH m.store s " +
            "WHERE um.member.id = :memberId " +
            "AND um.id < :cursorId")
    Slice<UserMission> findCompletedMissions( @Param("memberId") Long memberId, @Param("cursorId") Long cursorId,
            Pageable pageable // ORDER BY, LIMIT 정보를 서비스 계층에서 담아서 보낸게 담겨 있음
    );

    @Query("SELECT m FROM Mission m " +
            "JOIN FETCH m.store s " +
            "JOIN FETCH s.local l " +
            "LEFT JOIN m.userMissionList um " +
            "ON um.member.id = :memberId " +
            "WHERE l.id = :localId " +
            "AND m.id < :cursorId")
    Slice<Mission> findAvailableMissions(
            @Param("localId") Long localId,
            @Param("memberId") Long memberId,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );
}
