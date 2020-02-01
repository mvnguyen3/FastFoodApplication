package com.vntofu.fastfood.controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vntofu.fastfood.domain.Cart;
import com.vntofu.fastfood.domain.Customer;
import com.vntofu.fastfood.domain.Food;
import com.vntofu.fastfood.service.UnifiedService;

@Controller
@RequestMapping("/vntofu/customer")
public class CheckoutController {
	private static final Logger LOGGER=LoggerFactory.getLogger(CheckoutController.class);
	static {
		LOGGER.info("Checkout Controller");
	}
	
	@Autowired
	UnifiedService service;

	
	@RequestMapping("/ordersummary")
	ModelAndView orderSumnmary(Model model, HttpSession session, ModelAndView modelandview) {
		modelandview = new ModelAndView("orderSummary");
		model.addAttribute("food", new Food());
		HashSet<Food> foods = (HashSet<Food>) session.getAttribute("foods");
		Customer customer = (Customer) session.getAttribute("customer");
		calculateTotal(customer, foods, modelandview);
		return modelandview;
	}
	
	
	@RequestMapping("/checkout")
	ModelAndView checkOut(Model model, HttpSession session, ModelAndView modelandview) {
		modelandview = new ModelAndView("checkout");
		model.addAttribute("food", new Food());
		HashSet<Food> foods = (HashSet<Food>) session.getAttribute("foods");
		Customer customer = (Customer) session.getAttribute("customer");
		customer = calculateTotal(customer, foods, modelandview);
		if (!service.saveCustomer(customer))
			System.out.println("Failed to save customer!");
		
		return modelandview;
	}

	Customer calculateTotal(Customer customer, HashSet<Food> foods, ModelAndView modelandview) {
		// modelandview = new ModelAndView("checkout");

		
		double tax, subTotal = 0, total;
		for (Food f : foods) {
			subTotal += f.getFoodSubTotal();
		}
		tax = 0.1075 * subTotal;
		total = subTotal + tax;

		modelandview.addObject("tax", "$" + String.format("%.2f", tax));
		modelandview.addObject("subtotal", "$" + String.format("%.2f", subTotal));
		modelandview.addObject("total", "$" + String.format("%.2f", total));
		modelandview.addObject("customerName", customer.getCustomerName());

		customer.setTotal(total);
		Cart cart = customer.getCart();
		cart.setTax(tax);
		
		System.out.println("Check out Customer: " + customer);
		
		
		return customer;
	}
	
	@RequestMapping("/buymore")
	ModelAndView buymore(HttpSession session, RedirectAttributes redirectAttributes) {
		ModelAndView modelandview = new ModelAndView("redirect:/vntofu/customer/food");		
		Customer sessionCustomer = (Customer) session.getAttribute("customer");
		Set<Food> foods = new HashSet<Food>();
		Cart cart = new Cart(foods);
		Customer customer = new Customer(sessionCustomer.getCustomerName(), sessionCustomer.getCustomerPhone(), cart);		
		customer.setSessionCode(sessionCustomer.getSessionCode());
		
		session.setAttribute("foods", foods);
		session.setAttribute("cart", cart);
		session.setAttribute("customer", customer);
		session.setAttribute("secondTransaction", true);
				
		System.out.println("Session Renewed!!");						
		return modelandview;
	}
	
	@RequestMapping("/thankyou")
	ModelAndView thankyou(HttpSession session) {
		ModelAndView modelandview = new ModelAndView("thankyou");
		Customer customer = (Customer) session.getAttribute("customer");
		modelandview.addObject("customerName", customer.getCustomerName());
		System.out.println("Invalidating session...");
		session.invalidate();
		System.out.println("Session invalidated...");
		return modelandview;
		
	}
	
	
	/*
	 * @RequestMapping("/checkout") private void setTaxTotal(HttpSession session,
	 * Customer customer) { try {
	 * System.out.println("Saving customer to database ....."); Thread.sleep(2000);
	 * double tax = 0; HashSet<Food> foods = (HashSet<Food>)
	 * session.getAttribute("foods"); for (Food f : foods) { if
	 * (f.getFoodCategory().equals("by each")) { tax = 0.1075 * f.getFoodPrice() *
	 * f.getFoodQuantity(); f.setFoodSubTotal((f.getFoodPrice() *
	 * f.getFoodQuantity()) + tax); tax = 0; } else if
	 * (f.getFoodCategory().equals("by pieces")) { tax = 0.1075 * ((f.getFoodPrice()
	 * * f.getFoodQuantity()) / 6); f.setFoodSubTotal(((f.getFoodPrice() *
	 * f.getFoodQuantity()) / 6) + tax); tax = 0; } } // Save into database
	 * System.out.println("Check out Customer: " + customer); if
	 * (!service.saveCustomer(customer))
	 * System.out.println("Failed to save customer!"); } catch (InterruptedException
	 * e) { // TODO Auto-generated catch block e.printStackTrace(); } }
	 */

}
