package com.vntofu.fastfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vntofu.fastfood.domain.Food;

public interface FoodRepository extends JpaRepository<Food, Long>{
	
}
