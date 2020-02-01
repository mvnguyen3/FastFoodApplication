package com.vntofu.fastfood.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.vntofu.fastfood.domain.Food;

@Component
public class FoodValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Food.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Food food = (Food) target;
		if(food.getFoodQuantity() <= 0) {
			errors.rejectValue("foodQuantity", "food.foodQuantity.empty","Must not be empty");
		}
		
	}

}
