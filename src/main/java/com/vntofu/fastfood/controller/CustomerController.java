package com.vntofu.fastfood.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vntofu.fastfood.domain.Cart;
import com.vntofu.fastfood.domain.Customer;
import com.vntofu.fastfood.domain.Food;
import com.vntofu.fastfood.service.UnifiedService;
import com.vntofu.fastfood.validator.CustomerValidator;

@Controller
@RequestMapping("/vntofu/customer")
public class CustomerController {

private static final Logger LOGGER=LoggerFactory.getLogger(CustomerController.class);
	
	static {
		LOGGER.info("Customer Controller");
	}
	
	
	@Autowired
	UnifiedService service;

	@Autowired
	CustomerValidator customerValidator;

	@InitBinder
	void InitBinder(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(customerValidator);
	}

//	@RequestMapping(value = "/food/add", method = RequestMethod.GET)
//	ResponseEntity<?> addFood(@RequestBody Food food) {
//		foods.add(food);
//
//		return new ResponseEntity<List<Food>>(foods, HttpStatus.OK);
//	}
//
//	@RequestMapping(value = "/food/remove", method = RequestMethod.GET)
//	ResponseEntity<?> removeFood(@RequestParam(name = "index") int index) {
//		try {
//			if (foods.remove(index) != null)
//				return new ResponseEntity<List<Food>>(foods, HttpStatus.OK);
//		} catch (IndexOutOfBoundsException ioe) {
//			ioe.printStackTrace();
//		}
//
//		return new ResponseEntity<String>("Can't remove food", HttpStatus.BAD_REQUEST);
//	}
	
	@RequestMapping(value="/clientCall", method=RequestMethod.GET)
	String clientCall() {
		return "clientCall";
	}
	
	

	@PostMapping("/saveCustomer")
	ModelAndView processSaveCustomer(@ModelAttribute("customer") @Valid Customer customer, BindingResult br,
			HttpSession session, Model model, RedirectAttributes redirectAttributes) {
		// For ModelAttribute
		model.addAttribute("customer", new Customer());

		ModelAndView modelandview = new ModelAndView();
		if (br.hasErrors()) {
			System.out.println("An error encounter");
			redirectAttributes.addFlashAttribute("customerNameError", "Must Not Be Empty");
			redirectAttributes.addFlashAttribute("customerPhoneError", "Must Not Be Empty");
			modelandview.setViewName("redirect:/vntofu/customer/welcome");
			return modelandview;
		} else {
			// Declare and wire objects together
			Set<Food> foods = new HashSet<Food>();
			Cart staticCart = new Cart(foods);
			Customer staticCustomer = customer;
			staticCustomer.setCart(staticCart);

			String sessionNumber = generateSessionCode(staticCustomer.getCustomerName());
			staticCustomer.setSessionCode(sessionNumber);

			// Put object into session
			// System.out.println("Cusstomer object: " + staticCustomer);
			session.setAttribute("customer", staticCustomer);
			session.setAttribute("cart", staticCart);
			session.setAttribute("foods", foods);

			redirectAttributes.addFlashAttribute("status", "Successfully login!!");
			modelandview.setViewName("redirect:/vntofu/customer/food");

			return modelandview;
		}
	}
	

	private String generateSessionCode(String customerName) {
		String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJtZXNzYWdlIjoiSldUIFJ1bGVzISIsImlhdCI6MTQ1OTQ0ODExOSwiZXhwIjoxNDU5NDU0NTE5fQ.-yIVBD5b73C75osbmwwshQNRC7frWUYrqaTjTpza2y4";
		StringBuilder sb = new StringBuilder();
		for (char c : customerName.toCharArray()) {
			sb.append(c + "");
			sb.append(token.charAt((int) (Math.random() * 155) + 1));
		}
		System.out.println("Session Code: " + sb);
		return sb.toString();
	}

	@RequestMapping("/customerForm")
	ModelAndView customerFormView(ModelAndView view) {
		view.setViewName("welcome");

		return view;
	}

	// Adding ModelAttribute method
	@RequestMapping("/*")
	String welcomeMessage(Model model) {
		model.addAttribute("customer", new Customer());

		return "welcome";
	}

	
}




























