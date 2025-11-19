package com.springboot.umc9th.domain.member.repository;

import com.springboot.umc9th.domain.member.entity.Food;
import com.springboot.umc9th.domain.member.entity.mapping.MemberFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
