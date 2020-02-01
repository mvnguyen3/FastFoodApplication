package com.vntofu.fastfood.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.annotations.Synchronize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.vntofu.fastfood.domain.Customer;
import com.vntofu.fastfood.domain.Food;
import com.vntofu.fastfood.service.UnifiedService;

// Admin Use

@Controller
@RequestMapping("/vntofu")
public class UserController {
	
private static final Logger LOGGER=LoggerFactory.getLogger(UserController.class);
	
	static {
		LOGGER.info("User Controller");
	}
	
	
	@Autowired
	UnifiedService service;
	
	@RequestMapping("/viewCustomer")
	ModelAndView viewCustomer(ModelAndView modelandview) {
		modelandview = new ModelAndView("admin/viewcustomer");
		List<Customer> customers =  service.findAllCustomers();
		modelandview.addObject("customers", customers);
							
		return modelandview;
	}
	
	@RequestMapping("/adminAuth")
	String adminAuth() {
		return "admin/adminAuth";
	}
	
	@RequestMapping("/viewCart")
	ModelAndView viewCustomerCart(@RequestParam(name="customerId") long customerId) {
		ModelAndView modelandview = new ModelAndView("admin/viewcustomerCart");
		Customer customer = service.findCustomerById(customerId);
		System.out.println("Customer: " + customer);
		System.out.println("cart: " + customer.getCart());
		Set<Food> foods = customer.getCart().getFoods();
		System.out.println("foods: " + foods);
		modelandview.addObject("foods", foods);
		
		return modelandview;
		
	}
	
	
	
	
	// FlashAttribute object will get destroy after being used.
//	List<Customer> deletedCustomers = new ArrayList<Customer>();
	@RequestMapping("/deleteCustomer")
	RedirectView deleteCustomer(@RequestParam(name="customerId") long customerId, RedirectAttributes redirectAttributes) {
		RedirectView view = new RedirectView("/vntofu/viewCustomer");
		
		
		Customer customer = service.deleteCustomerById(customerId);
		
		System.out.println("Deleted customer with customerId: " + customerId);
		
		try {
				Thread.sleep(5000);
		}catch(InterruptedException ie) {
			System.out.println();
		}
		System.out.println("dsafawehof");
		System.out.println("Done !!!");
		redirectAttributes.addFlashAttribute("status", "Successfully deleted");				
		return view;				
	}
	
//	@RequestMapping("/rollbackCustomer")
//	ModelAndView rollbackCustomer(RedirectAttributes redirectAttributes) {
//		ModelAndView modelandview = new ModelAndView("admin/viewcustomer");
//	
//		
//		modelandview.addObject("customers", service.findAllCustomers());
//		return modelandview;
//	}
	
	
	
	
	
	
	
	
	
	
	
	// *************** LOGIN LOGOUT ************
		int loginCount = 0;
		@RequestMapping({"/adminLogin", "/adminLogin/*"})		
		public ModelAndView logToAp(@RequestParam(value = "error", required = false) String error,
				@RequestParam(value = "logout", required = false) String logout, HttpServletRequest req,
				HttpServletResponse res, Model model, HttpSession session) {
			ModelAndView modelandview = new ModelAndView("adminLogin");
			System.out.println("Enter login methodd....");
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String errorMessage = null;
			
			// Initializing

			// ***********
			
			if (error != null) {
				errorMessage = "Either username or password is incorrect";
				System.out.println("error: " + errorMessage);
				
				if(loginCount == 3) {
					loginCount++;
					return new ModelAndView("accessDeniedPage");
				}
				else if(loginCount >= 6) {
					try {
						Thread.sleep(5000);
						return new ModelAndView("accessDeniedPage");
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				}
				System.out.println("Login COunt:" + loginCount);
				loginCount++;
			}
			if (logout != null) {
				if (auth != null) {
					new SecurityContextLogoutHandler().logout(req, res, auth);
				}
				errorMessage = "You have been logged out sucessfully";
			}

			System.out.println("auth: " + auth );
			model.addAttribute("errorMessage", errorMessage);
			return modelandview;
		}	
}
