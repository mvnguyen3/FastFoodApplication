package com.vntofu.fastfood.service;

import org.springframework.stereotype.Service;

import com.vntofu.fastfood.domain.Food;


public interface FoodService {
	boolean saveFood(Food food);
}
