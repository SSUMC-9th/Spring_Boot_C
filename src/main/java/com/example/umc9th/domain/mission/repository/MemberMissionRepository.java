package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.dto.response.MyMissionResponseDTO;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    // 특정 회원 ID와 미션 상태를 기준으로 MemberMission 목록을 조회하며, 페이징 적용
    // return: 페이징된 MemberMission 엔티티 목록

    // 진행 완료(1), 진행중(0)인 미션 조회
    @Query("SELECT new com.example.umc9th.domain.mission.dto.response.MyMissionResponseDTO(mm.id, mm.mission.point, mm.mission.store.name, mm.mission.content, mm.status) " +
            "FROM MemberMission mm " +
            "WHERE mm.member.id = :memberId and mm.status = :status " +
            "ORDER BY mm.id desc")
    Page<MyMissionResponseDTO> findByMemberIdAndStatus(@Param("memberId") Long memberId, @Param("status") boolean status, Pageable pageable);

}
