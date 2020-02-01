package com.vntofu.fastfood.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.vntofu.fastfood.domain.Customer;
import com.vntofu.fastfood.domain.Food;
import com.vntofu.fastfood.repository.CustomerRepository;
import com.vntofu.fastfood.repository.FoodRepository;

@Service
@Transactional
public class UnifiedServiceImpl implements UnifiedService{

	//private static final Logger LOGGER = LoggerFactory.getLogger(UnifiedServiceImpl.class);
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	FoodRepository foodRepository;
	

	@Override
	public boolean saveCustomer(Customer customer) {
		try {
			
			customerRepository.save(customer);
			System.out.println("Successfully save customer: " + customer.getCustomerName());
			return true;
		}catch(IllegalArgumentException ie) {
			ie.printStackTrace();
			return false;
		}
		
	}
	

	@Override
	public boolean saveFood(Food food) {
		try {
			foodRepository.save(food);
			return true;
		}catch(IllegalArgumentException e) {
			System.out.println("Can't save the food Object");
			return false;
		}
	}

	@Override
	public List<Customer> findAllCustomers() {	
		return customerRepository.findAll();
	}


	@Override
	public Customer findCustomerById(long id) {
		
		return customerRepository.findById(id).get();
	}


	@Override
	public Customer deleteCustomerById(long id) {
		Customer c = null;
		try {
			c = customerRepository.findById(id).get();
			customerRepository.deleteById(id);
			
		}catch(NoSuchElementException e) {
			System.out.println(e);
		}
		
		return c;
	}

}






























