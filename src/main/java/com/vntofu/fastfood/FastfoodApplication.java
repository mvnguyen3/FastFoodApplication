package com.vntofu.fastfood;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FastfoodApplication {
	private static final Logger LOGGER=LoggerFactory.getLogger(FastfoodApplication.class);
	public static void main(String[] args) {		
		SpringApplication.run(FastfoodApplication.class, args);
		LOGGER.info("MAIN");
		
		// default customer link:   /vntofu/customer/
	}
	
	
	//TODO - code:  ---    asdfwaef123   ---    on food.jsp  Add more food to food.jsp
		/*
		 * 
		 * */
	
	// TODO
	/*
	 * Prevent page from refresh when customer add food to cart - Use AJAX
	 * Add javascript to calculate and display to Status... food.jsp
	 * 
	 * 
	 * */
	
	// TODO
	/*
	 * Minimize the times contacting with the database On Admin use
	 * Communicate with database 2 times only.
	 * 	+ Load data from database only 1 time at the beginning and load only 1 time when user exit.
	 * 
	 */
	
	
	
	
	
	// - DONE	
	/* 
	 * Implementing Admin mode...
	 * 	+ Admin can view customer details And delete food records DONE
	 * 		+ Adding customer table to viewCustomer
	 * 	+ Add Spring security.... Done Used - inMemmoryAuthentication 
	 */
	
						
	
	
	// Done code:  ---    as!@#*&^RI   ---    on cart.jsp  
	// Implement Delete button on cart.jsp
	
	
	
	
	//Done   
	/*
	 * Implement Cart.jsp
	 * Customer can: 
	 * 	+ View all the food she bought in the cart
	 * 	+ Adjust Quantites
	 * In progress + Check out - Final Step
	 * 	+ Create an check out page:
	 * 		+ View user summary orders 
	 * 		+ Save customer into database
	 */
	
	
	
	// DONE
		/*
		 * The cart object is being assign to a specific customer.
		 * When having the customer login, Redirect them to order site...
		 * 
		 * 
		 */
	
	// DONE
	/*
	 * Create an API request to store Food object into Food collection.
	 * Food collection can be access from CustomerController
	 * 
	 */

}
