package com.example.umc9th.domain.food.repository;

import com.example.umc9th.domain.member.entity.Food;
import com.example.umc9th.domain.member.enums.FoodName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

}
