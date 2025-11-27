package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 여러개의 리뷰를 모두 가져오므로 Page<Review>로 반환 (페이지네이션)
    Page<Mission> findAllByStoreId(Long storeId, Pageable pageable);
}
