package com.example.UMCChapter4.domain.mission.repository;

import com.example.UMCChapter4.domain.mission.entity.MemberMission;
import com.example.UMCChapter4.domain.mission.entity.Mission;
import com.example.UMCChapter4.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    @Query(value = "SELECT m FROM Mission m " +
            "JOIN FETCH m.store s " +
            "WHERE m.store = :store",
            countQuery = "SELECT COUNT(m) FROM Mission m WHERE m.store = :store")
    Page<Mission> findAllByStore(
            @Param("store") Store store,
            Pageable pageable
    );
}
