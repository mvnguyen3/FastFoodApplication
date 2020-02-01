package com.vntofu.fastfood.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vntofu.fastfood.domain.Cart;
import com.vntofu.fastfood.domain.Customer;
import com.vntofu.fastfood.domain.Food;
import com.vntofu.fastfood.service.UnifiedService;

@RestController
@RequestMapping("/vntofu/ajax")
public class AjaxController {
	private static final Logger LOGGER=LoggerFactory.getLogger(AjaxController.class);
	static {
		LOGGER.info("Ajax Controller");
	}
	
	@Autowired
	UnifiedService service;

	@RequestMapping(value="/getCartLength", method = RequestMethod.GET)
	ResponseEntity<?> getCartLength(HttpSession session){
		
		Set<Food> foods = (Set<Food>) session.getAttribute("foods");
		if(foods.isEmpty())
			return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);					
		
		int cartSize = foods.size();
		
		
		return new ResponseEntity<String>(cartSize + " items in cart", HttpStatus.OK);
	}
	
	
	
	
	@RequestMapping(value = "/getCustomer", method = RequestMethod.GET)
	ResponseEntity<?> ajaxResponse() {
		List<Customer> customers = service.findAllCustomers();
		System.out.println("All customers: " + customers);
		String response = "Response";
		System.out.println("hello");
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);

	}
	// Doesn't work No serializer found for class org.hibernate.proxy.pojo.javassist.Javassist?
	
//	@RequestMapping(value = "/getCart")
//	ResponseEntity<?> ajaxCartResponse(@RequestParam(name = "cartId") long cartId){
//		List<Customer> customers = service.findAllCustomers();
//		Cart cart = null;
//		System.out.println("All customers: " + customers);
//		for (Customer c : customers) {
//			if (c.getCart().getCartId() == cartId) {
//				cart = c.getCart();
//				break;
//			}
//
//		}
//		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
//	}
	
	@RequestMapping(value = "/getFoods", method = RequestMethod.GET)
	ResponseEntity<?> ajaxFoodsResponse(@RequestParam(name = "customerId") long customerId) {
		List<Customer> customers = service.findAllCustomers();
		Cart cart = null;
		System.out.println("All customers: " + customers);
		for (Customer c : customers) {
			if (c.getCart().getCartId() == customerId) {
				cart = c.getCart();
				break;
			}

		}
		try {
			if(cart != null) {
				Set<Food> foods = cart.getFoods();
				
				return new ResponseEntity<Set<Food>>(foods, HttpStatus.OK);
			}
		}catch(NullPointerException e) {
			
		}
	
		return new ResponseEntity<String>("No customer found", HttpStatus.BAD_GATEWAY);		
	}

}


























