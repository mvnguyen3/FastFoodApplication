package com.vntofu.fastfood.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity(name = "Cart")
public class Cart {
	
	@Id
	@Column(name="Cart_Id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long cartId;
	
	private double tax;
	
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JsonBackReference	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="cart", cascade = CascadeType.ALL)	 	
	private Set<Food> foods;
	

	public Cart() {
		
	}
	
	public Cart(Set<Food> foods) {
		super();
		this.foods = foods;
		
	}

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public Set<Food> getFoods() {
		return foods;
	}

	public void setFoods(Set<Food> foods) {
		this.foods = foods;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", tax=" + tax + "]" + "foods: " + foods;
	}	
	
}