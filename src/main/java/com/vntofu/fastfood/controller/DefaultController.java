package com.vntofu.fastfood.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultController {
private static final Logger LOGGER=LoggerFactory.getLogger(DefaultController.class);
	
	static {
		LOGGER.info("Default Controller");
	}
	
	@RequestMapping("/")		
	ModelAndView homePage() {
		ModelAndView modelandview = new ModelAndView("redirect:/vntofu/customer/");
		
		return modelandview;
		
	}
	
	@RequestMapping("/*")
	String accessDenied() {
		return "accessDeniedPage";
	}
	
	
}
