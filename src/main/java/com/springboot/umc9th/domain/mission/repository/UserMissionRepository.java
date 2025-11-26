package com.springboot.umc9th.domain.mission.repository;

import com.springboot.umc9th.domain.member.entity.Member;
import com.springboot.umc9th.domain.mission.entity.mapping.UserMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    Page<UserMission> findAllByMemberAndComplete(Member member, Boolean complete, Pageable pageable);

}