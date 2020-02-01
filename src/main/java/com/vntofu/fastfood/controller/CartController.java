package com.vntofu.fastfood.controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vntofu.fastfood.domain.Cart;
import com.vntofu.fastfood.domain.Food;
import com.vntofu.fastfood.service.UnifiedService;
import com.vntofu.fastfood.validator.FoodValidator;

@Controller
@RequestMapping("/vntofu/customer")
public class CartController {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(CartController.class);
	static {
		LOGGER.info("Cart Controller");
	}
	
	
	
	@Autowired
	UnifiedService service;

	@Autowired
	FoodValidator foodValidator;

	@InitBinder
	void InitBinder(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(foodValidator);
	}

	@RequestMapping("/cart")
	String cartForm(Model model, HttpSession session) {
		model.addAttribute("food", new Food());
		// model.addAttribute("cart", new Cart());
		calculateSubTotal(session);

		return "cart";
	}

	private void calculateSubTotal(HttpSession session) {

		HashSet<Food> foods = (HashSet<Food>) session.getAttribute("foods");
		for (Food f : foods) {
			if (f.getFoodCategory().equals("by each")) {
				f.setFoodSubTotal(f.getFoodPrice() * f.getFoodQuantity());
			} else if (f.getFoodCategory().equals("by pieces")) {
				f.setFoodSubTotal((f.getFoodPrice() * f.getFoodQuantity()) / 6);
			}
		}
	}

	@PostMapping("/updateCart")
	ModelAndView updateCart(@ModelAttribute @Valid Food food, BindingResult br, HttpSession session,
			RedirectAttributes redirectAttributes, Model model) {
		ModelAndView modelandview = new ModelAndView();
		model.addAttribute("cart", new Cart());

		if (br.hasErrors()) {
			redirectAttributes.addFlashAttribute("status", "Quantity can't be 0");
			modelandview.setViewName("redirect/vntofu/customer/cart");
			return modelandview;
		}

		HashSet<Food> foods = (HashSet<Food>) session.getAttribute("foods");
		for (Food f : foods) {
			if (f.getFoodName().equals(food.getFoodName())) {

				if (f.getFoodQuantity() != food.getFoodQuantity()) {
					f.setFoodQuantity(food.getFoodQuantity());
					redirectAttributes.addFlashAttribute("status", "Quantity Updated");
				} else
					redirectAttributes.addFlashAttribute("status", " ");
				break;
			}
		}

		modelandview.setViewName("redirect:/vntofu/customer/cart");
		return modelandview;
	}

	@GetMapping("/removeCartItem")
	ModelAndView removeCartItem(@RequestParam(name = "foodName") String foodName, HttpSession session,
			RedirectAttributes redirectAttributes, Model model) {
		ModelAndView modelandview = new ModelAndView();
		model.addAttribute("cart", new Cart());
		HashSet<Food> foods = (HashSet<Food>) session.getAttribute("foods");
		Iterator<Food> iterator = foods.iterator();
		while (iterator.hasNext()) {
			Food f = iterator.next();
			if (f.getFoodName().equals(foodName)) {
				iterator.remove();
				iterator = foods.iterator();
				break;
			}
		}
		if (foods.isEmpty())
			modelandview.setViewName("redirect:/vntofu/customer/food");
		else
			modelandview.setViewName("redirect:/vntofu/customer/cart");

		System.out.println("Removed: " + foodName);
		redirectAttributes.addFlashAttribute("status", "Food Removed");
		return modelandview;
	}

	/*
	 * @PostMapping("/checkout") ModelAndView checkOut(HttpSession session, Model
	 * model) { model.addAttribute("food", new Food()); ModelAndView modelandview =
	 * new ModelAndView("cart"); try { Cart cartSession = (Cart)
	 * session.getAttribute("cart"); String customerName =
	 * cartSession.getCustomer().getCustomerName();
	 * 
	 * HashSet<Food> foods = (HashSet<Food>) cartSession.getFoods(); double total =
	 * 0; for (Food f : foods) { if (f.getFoodCategory().equals("by each")) { total
	 * += f.getFoodPrice() * f.getFoodQuantity(); } else if
	 * (f.getFoodCategory().equals("by pieces")) { total += (f.getFoodPrice() *
	 * f.getFoodQuantity()) / 6; }
	 * 
	 * } total += total * .1075; // Added tax service System.out.println("Total: " +
	 * total);
	 * 
	 * modelandview.addObject("status", "Thank You " + customerName);
	 * modelandview.addObject("checkoutStatus", "Check out Succesfully");
	 * modelandview.addObject("total", "$" + total); } catch (NullPointerException
	 * e) { e.printStackTrace(); modelandview.addObject("checkoutStatus",
	 * "Received 0 item");
	 * 
	 * }
	 * 
	 * return modelandview;
	 * 
	 * }
	 */

}
