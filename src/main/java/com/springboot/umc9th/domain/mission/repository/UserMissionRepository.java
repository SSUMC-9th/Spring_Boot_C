package com.springboot.umc9th.domain.mission.repository;

import com.springboot.umc9th.domain.mission.entity.mapping.UserMission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
}