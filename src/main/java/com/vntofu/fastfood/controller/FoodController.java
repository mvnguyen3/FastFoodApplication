package com.vntofu.fastfood.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vntofu.fastfood.FastfoodApplication;
import com.vntofu.fastfood.domain.Cart;
import com.vntofu.fastfood.domain.Customer;
import com.vntofu.fastfood.domain.Food;
import com.vntofu.fastfood.service.UnifiedService;
import com.vntofu.fastfood.validator.FoodValidator;

@Controller
@RequestMapping("/vntofu/customer")
public class FoodController {
	private static final Logger LOGGER=LoggerFactory.getLogger(FoodController.class);
	
	static {
		LOGGER.info("Food Controller");
	}
	
	@Autowired
	UnifiedService service;

	@Autowired
	FoodValidator foodValidator;

	@InitBinder
	void InitBinder(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(foodValidator);
	}

	@RequestMapping("/food")
	String view(Model model) {
		model.addAttribute("food", new Food());

		return "food";
	}
	

	@PostMapping("/addTofuToCart")
	ModelAndView addTofuToCart(@ModelAttribute @Valid Food food, BindingResult br, HttpSession session, Model model,
			RedirectAttributes redirectAttributes) {
		model.addAttribute("food", new Food()); // Refresh the page
		
		ModelAndView modelandview = new ModelAndView("food");
		

		// Validation
		if (br.hasErrors()) {
			System.out.println("Quantity can't be 0");
			modelandview.setViewName("redirect:/vntofu/customer/food");
			redirectAttributes.addFlashAttribute("error", "Quantity can't be 0");
			return modelandview;

		}
		//addTofuToCartHelper(session, food);
		Thread t = new Thread(() -> addTofuToCartHelper(session, food));;
		t.start();

		return modelandview;
	}

	private synchronized void addTofuToCartHelper(HttpSession session, Food food) {
		HashSet<Food> foods;
		Cart cart;

		try {
			foods = (HashSet<Food>) session.getAttribute("foods");
			cart = (Cart) session.getAttribute("cart");
//			System.out.println("foods: " + foods);
			System.out.println("cart: " + cart);
			
			
			
			// Add 1 time
			boolean firstTime = false;
			if (!foods.contains(food)) {				
				food.setCart(cart);
				foods.add(food);
				firstTime = true;

			}

			final String foodName = food.getFoodName();
			for (Food f : foods) {
				switch (foodName) {

				case "Plain Tofu":

					// Update Quantity
					if (f.getFoodName().equals("Plain Tofu") && firstTime) {
						f.setFoodQuantity(food.getFoodQuantity());						
						//modelandview.addObject("plainTofuQuantity", f.getFoodQuantity() + " items on cart");
					} else if (f.getFoodName().equals("Plain Tofu")) {
						f.setFoodQuantity(f.getFoodQuantity() + food.getFoodQuantity());						
						//modelandview.addObject("plainTofuQuantity", f.getFoodQuantity() + " items on cart");
					}

					break;

				case "Big Tofu":

					// Update Quantity
					if (f.getFoodName().equals("Big Tofu") && firstTime) {
						f.setFoodQuantity(food.getFoodQuantity());
						//modelandview.addObject("bigTofuQuantity", f.getFoodQuantity() + " items on cart");
					} else if (f.getFoodName().equals("Big Tofu")) {
						f.setFoodQuantity(f.getFoodQuantity() + food.getFoodQuantity());
						//modelandview.addObject("bigTofuQuantity", f.getFoodQuantity() + " items on cart");

					}

					break;

				case "Spicy Tofu":

					// Update Quantity
					if (f.getFoodName().equals("Spicy Tofu") && firstTime) {
						f.setFoodQuantity(food.getFoodQuantity());
						//modelandview.addObject("spicyTofuQuantity", f.getFoodQuantity() + " items on cart");
					} else if (f.getFoodName().equals("Spicy Tofu")) {
						f.setFoodQuantity(f.getFoodQuantity() + food.getFoodQuantity());
						//modelandview.addObject("spicyTofuQuantity", f.getFoodQuantity() + " items on cart");

					}

					break;

				case "Onion Tofu":

					// Update Quantity
					if (f.getFoodName().equals("Onion Tofu") && firstTime) {
						f.setFoodQuantity(food.getFoodQuantity());
						//modelandview.addObject("onionTofuQuantity", f.getFoodQuantity() + " items on cart");
					} else if (f.getFoodName().equals("Onion Tofu")) {
						f.setFoodQuantity(f.getFoodQuantity() + food.getFoodQuantity());
						//modelandview.addObject("onionTofuQuantity", f.getFoodQuantity() + " items on cart");

					}
					break;

				case "Cilantro Tofu":

					// Update Quantity
					if (f.getFoodName().equals("Cilantro Tofu") && firstTime) {
						f.setFoodQuantity(food.getFoodQuantity());
						//modelandview.addObject("cilantroTofuQuantity", f.getFoodQuantity() + " items on cart");
					} else if (f.getFoodName().equals("Cilantro Tofu")) {
						f.setFoodQuantity(f.getFoodQuantity() + food.getFoodQuantity());
						//modelandview.addObject("cilantroTofuQuantity", f.getFoodQuantity() + " items on cart");

					}
					break;

				case "Mushroom Tofu":

					// Update Quantity
					if (f.getFoodName().equals("Mushroom Tofu") && firstTime) {
						f.setFoodQuantity(food.getFoodQuantity());
						//modelandview.addObject("mushroomTofuQuantity", f.getFoodQuantity() + " items on cart");
					} else if (f.getFoodName().equals("Mushroom Tofu")) {
						f.setFoodQuantity(f.getFoodQuantity() + food.getFoodQuantity());
						//modelandview.addObject("mushroomTofuQuantity", f.getFoodQuantity() + " items on cart");

					}
					break;
				case "Ky Tofu":

					// Update Quantity
					if (f.getFoodName().equals("Ky Tofu") && firstTime) {
						f.setFoodQuantity(food.getFoodQuantity());
						//modelandview.addObject("kyTofuQuantity", f.getFoodQuantity() + " items on cart");
					} else if (f.getFoodName().equals("Ky Tofu")) {
						f.setFoodQuantity(f.getFoodQuantity() + food.getFoodQuantity());
						//modelandview.addObject("kyTofuQuantity", f.getFoodQuantity() + " items on cart");

					}
					break;

				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("Can't load foods and carts object");
			
		}
		
	}

	

//	@PostMapping("/addBigTofuToCart")
//	ModelAndView addBigTofuToCart(@ModelAttribute @Valid Food food, BindingResult br, HttpSession session, Model model,
//			RedirectAttributes redirectAttributes) {
//		model.addAttribute("food", new Food());
//		model.addAttribute("customer", new Customer());
//		model.addAttribute("cart", new Cart());
//
//		int quantity = 0;
//		ModelAndView modelandview = new ModelAndView("food");
//
//		// Validation
//		if (br.hasErrors()) {
//			System.out.println("Quantity can't be 0");
//			modelandview.setViewName("redirect:/food");
//			redirectAttributes.addFlashAttribute("error", "Quantity can't be 0");
//			return modelandview;
//
//		}
//
//		List<Food> foods;
//		Cart cart;
//		boolean found = false;
//
//		try {
//			foods = (List<Food>) session.getAttribute("foods");
//			cart = (Cart) session.getAttribute("cart");
//			System.out.println("foods: " + foods);
//			System.out.println("cart: " + cart);
//
//			// Update Quantity
//			for (Food f : foods) {
//				if (f.getFoodName().equals("Big Tofu")) {
//					found = true;
//					f.setFoodQuantity(f.getFoodQuantity() + food.getFoodQuantity()); // Update quantity
//					quantity += f.getFoodQuantity();
//				}
//			}
//			// Only Add 1 time
//			if (found == false) {
//				foods.add(food);
//				quantity += food.getFoodQuantity();
//			}
//
//		} catch (NullPointerException e) {
//			e.printStackTrace();
//			System.out.println("Can't load foods and carts object");
//			modelandview.setViewName("welcome");
//			return modelandview;
//		}
//
//		System.out.println("Added: " + food);
//		modelandview.addObject("bigTofuQuantity", quantity + " items on cart");
//		return modelandview;
//	}

//	@PostMapping("/addSpicyTofuToCart")
//	ModelAndView addSpicyTofuToCart(@ModelAttribute @Valid Food food, BindingResult br, HttpSession session,
//			Model model, RedirectAttributes redirectAttributes) {
//		model.addAttribute("food", new Food());
//		model.addAttribute("customer", new Customer());
//		model.addAttribute("cart", new Cart());
//
//		int quantity = 0;
//		ModelAndView modelandview = new ModelAndView("food");
//
//		// Validation
//		if (br.hasErrors()) {
//			System.out.println("Quantity can't be 0");
//			modelandview.setViewName("redirect:/food");
//			redirectAttributes.addFlashAttribute("error", "Quantity can't be 0");
//			return modelandview;
//
//		}
//
//		List<Food> foods;
//		Cart cart;
//		boolean found = false;
//
//		try {
//			foods = (List<Food>) session.getAttribute("foods");
//			cart = (Cart) session.getAttribute("cart");
//			System.out.println("foods: " + foods);
//			System.out.println("cart: " + cart);
//
//			// Update Quantity
//			for (Food f : foods) {
//				if (f.getFoodName().equals("Spicy Tofu")) {
//					found = true;
//					f.setFoodQuantity(f.getFoodQuantity() + food.getFoodQuantity()); // Update quantity
//					quantity += f.getFoodQuantity();
//				}
//			}
//			// Only Add 1 time
//			if (found == false) {
//				foods.add(food);
//				quantity += food.getFoodQuantity();
//			}
//
//		} catch (NullPointerException e) {
//			e.printStackTrace();
//			System.out.println("Can't load foods and carts object");
//			modelandview.setViewName("welcome");
//			return modelandview;
//		}
//
//		System.out.println("Added: " + food);
//		modelandview.addObject("spicyTofuQuantity", quantity + " items on cart");
//		return modelandview;
//	}
//
//	@PostMapping("/addOnionTofuToCart")
//	ModelAndView addOnionTofuToCart(@ModelAttribute @Valid Food food, BindingResult br, HttpSession session,
//			Model model, RedirectAttributes redirectAttributes) {
//		model.addAttribute("food", new Food());
//		model.addAttribute("customer", new Customer());
//		model.addAttribute("cart", new Cart());
//
//		int quantity = 0;
//		ModelAndView modelandview = new ModelAndView("food");
//
//		// Validation
//		if (br.hasErrors()) {
//			System.out.println("Quantity can't be 0");
//			modelandview.setViewName("redirect:/food");
//			redirectAttributes.addFlashAttribute("error", "Quantity can't be 0");
//			return modelandview;
//
//		}
//
//		List<Food> foods;
//		Cart cart;
//		boolean found = false;
//
//		try {
//			foods = (List<Food>) session.getAttribute("foods");
//			cart = (Cart) session.getAttribute("cart");
//			System.out.println("foods: " + foods);
//			System.out.println("cart: " + cart);
//
//			// Update Quantity
//			for (Food f : foods) {
//				if (f.getFoodName().equals("Onion Tofu")) {
//					found = true;
//					f.setFoodQuantity(f.getFoodQuantity() + food.getFoodQuantity()); // Update quantity
//					quantity += f.getFoodQuantity();
//				}
//			}
//			// Only Add 1 time
//			if (found == false) {
//				foods.add(food);
//				quantity += food.getFoodQuantity();
//			}
//
//		} catch (NullPointerException e) {
//			e.printStackTrace();
//			System.out.println("Can't load foods and carts object");
//			modelandview.setViewName("welcome");
//			return modelandview;
//		}
//
//		System.out.println("Added: " + food);
//		modelandview.addObject("onionTofuQuantity", quantity + " items on cart");
//		return modelandview;
//	}
//
//	@PostMapping("/addCilantroTofuToCart")
//	ModelAndView addCilantroTofuToCart(@ModelAttribute @Valid Food food, BindingResult br, HttpSession session,
//			Model model, RedirectAttributes redirectAttributes) {
//		model.addAttribute("food", new Food());
//		model.addAttribute("customer", new Customer());
//		model.addAttribute("cart", new Cart());
//
//		int quantity = 0;
//		ModelAndView modelandview = new ModelAndView("food");
//
//		// Validation
//		if (br.hasErrors()) {
//			System.out.println("Quantity can't be 0");
//			modelandview.setViewName("redirect:/food");
//			redirectAttributes.addFlashAttribute("error", "Quantity can't be 0");
//			return modelandview;
//
//		}
//
//		List<Food> foods;
//		Cart cart;
//		boolean found = false;
//
//		try {
//			foods = (List<Food>) session.getAttribute("foods");
//			cart = (Cart) session.getAttribute("cart");
//			System.out.println("foods: " + foods);
//			System.out.println("cart: " + cart);
//
//			// Update Quantity
//			for (Food f : foods) {
//				if (f.getFoodName().equals("Cilantro Tofu")) {
//					found = true;
//					f.setFoodQuantity(f.getFoodQuantity() + food.getFoodQuantity()); // Update quantity
//					quantity += f.getFoodQuantity();
//				}
//			}
//			// Only Add 1 time
//			if (found == false) {
//				foods.add(food);
//				quantity += food.getFoodQuantity();
//			}
//
//		} catch (NullPointerException e) {
//			e.printStackTrace();
//			System.out.println("Can't load foods and carts object");
//			modelandview.setViewName("welcome");
//			return modelandview;
//		}
//
//		System.out.println("Added: " + food);
//		modelandview.addObject("cilantroTofuQuantity", quantity + " items on cart");
//		return modelandview;
//	}
//
//	@PostMapping("/addMushroomTofuToCart")
//	ModelAndView addMushroomTofuToCart(@ModelAttribute @Valid Food food, BindingResult br, HttpSession session,
//			Model model, RedirectAttributes redirectAttributes) {
//		model.addAttribute("food", new Food());
//		model.addAttribute("customer", new Customer());
//		model.addAttribute("cart", new Cart());
//
//		int quantity = 0;
//		ModelAndView modelandview = new ModelAndView("food");
//
//		// Validation
//		if (br.hasErrors()) {
//			System.out.println("Quantity can't be 0");
//			modelandview.setViewName("redirect:/food");
//			redirectAttributes.addFlashAttribute("error", "Quantity can't be 0");
//			return modelandview;
//
//		}
//
//		List<Food> foods;
//		Cart cart;
//
//		boolean found = false;
//
//		try {
//			foods = (List<Food>) session.getAttribute("foods");
//			cart = (Cart) session.getAttribute("cart");
//			System.out.println("foods: " + foods);
//			System.out.println("cart: " + cart);
//
//			// Update Quantity
//			for (Food f : foods) {
//				if (f.getFoodName().equals("Mushroom Tofu")) {
//					found = true;
//					f.setFoodQuantity(f.getFoodQuantity() + food.getFoodQuantity()); // Update quantity
//					quantity += f.getFoodQuantity();
//				}
//			}
//			// Only Add 1 time
//			if (found == false) {
//				foods.add(food);
//				quantity += food.getFoodQuantity();
//			}
//
//		} catch (NullPointerException e) {
//			e.printStackTrace();
//			System.out.println("Can't load foods and carts object");
//			modelandview.setViewName("welcome");
//			return modelandview;
//		}
//
//		System.out.println("Added: " + food);
//		modelandview.addObject("mushroomTofuQuantity", quantity + " items on cart");
//		return modelandview;
//	}
//
//	@PostMapping("/addKyTofuToCart")
//	ModelAndView addKyTofuToCart(@ModelAttribute @Valid Food food, BindingResult br, HttpSession session, Model model,
//			RedirectAttributes redirectAttributes) {
//		model.addAttribute("food", new Food());
//		model.addAttribute("customer", new Customer());
//		model.addAttribute("cart", new Cart());
//
//		int quantity = 0;
//		ModelAndView modelandview = new ModelAndView("food");
//
//		// Validation
//		if (br.hasErrors()) {
//			System.out.println("Quantity can't be 0");
//			modelandview.setViewName("redirect:/food");
//			redirectAttributes.addFlashAttribute("error", "Quantity can't be 0");
//			return modelandview;
//
//		}
//
//		List<Food> foods;
//		Cart cart;
//
//		boolean found = false;
//
//		try {
//			foods = (List<Food>) session.getAttribute("foods");
//			cart = (Cart) session.getAttribute("cart");
//			System.out.println("foods: " + foods);
//			System.out.println("cart: " + cart);
//
//			// Update Quantity
//			for (Food f : foods) {
//				if (f.getFoodName().equals("Ky Tofu")) {
//					found = true;
//					f.setFoodQuantity(f.getFoodQuantity() + food.getFoodQuantity()); // Update quantity
//					quantity += f.getFoodQuantity();
//				}
//			}
//			// Only Add 1 time
//			if (found == false) {
//				foods.add(food);
//				quantity += food.getFoodQuantity();
//			}
//
//		} catch (NullPointerException e) {
//			e.printStackTrace();
//			System.out.println("Can't load foods and carts object");
//			modelandview.setViewName("welcome");
//			return modelandview;
//		}
//
//		System.out.println("Added: " + food);
//		modelandview.addObject("kyTofuQuantity", quantity + " items on cart");
//		return modelandview;
//	}

//	void processFoods(HttpSession session) {
//		List<Food> foods = (List<Food>) session.getAttribute("foods");
//		for(Food f: foods) {
//			if(f.getFoodName().equals("Plain Tofu")) {
//				
//			}
//			
//		}
//		
//	}
}
