package com.vntofu.fastfood.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.vntofu.fastfood.domain.Customer;
import com.vntofu.fastfood.service.UnifiedService;

@Component
public class CustomerValidator implements Validator{

	@Autowired
	UnifiedService service;
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return Customer.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Customer customer = (Customer) target;	
		// Validate if customer name and phone are empty
		if(customer.getCustomerName().equals("")) {
			errors.rejectValue("customerName", "customer.customerName.empty", "Must not be empty");
		}if(customer.getCustomerPhone().equals("")) {
			errors.rejectValue("customerPhone", "customer.customerPhone.empty", "Must not be empty");
		}
		
	}

}
