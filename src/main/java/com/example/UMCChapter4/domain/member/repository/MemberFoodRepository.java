package com.example.UMCChapter4.domain.member.repository;

import com.example.UMCChapter4.domain.member.entity.mapping.MemberFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberFoodRepository extends JpaRepository<MemberFood,Long> {
}
