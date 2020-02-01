package com.vntofu.fastfood.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;


@Entity(name="Food")
public class Food {
	@Id
	@Column(name="Food_Id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long foodId;
	private String foodCategory;
	private String foodName;
	private double foodPrice;
	private long foodQuantity;
	private double foodSubTotal;
	
	@JsonBackReference
	@ManyToOne
	private Cart cart;
	
	public Food(){}

	public Food(String foodCategory, String foodName, double foodPrice, long foodQuantity) {				
		this.foodCategory = foodCategory;
		this.foodName = foodName;
		this.foodPrice = foodPrice;
		this.foodQuantity = foodQuantity;		
	}
	

	public double getFoodSubTotal() {
		return foodSubTotal;
	}

	public void setFoodSubTotal(double foodSubTotal) {
		this.foodSubTotal = foodSubTotal;
	}

	public long getFoodId() {
		return foodId;
	}

	public void setFoodId(long foodId) {
		this.foodId = foodId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public double getFoodPrice() {
		return foodPrice;
	}

	public void setFoodPrice(double foodPrice) {
		this.foodPrice = foodPrice;
	}

	public Long getFoodQuantity() {
		return foodQuantity;
	}

	public void setFoodQuantity(long foodQuantity) {
		this.foodQuantity = foodQuantity;
	}

	
	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public String getFoodCategory() {
		return foodCategory;
	}

	public void setFoodCategory(String foodCategory) {
		this.foodCategory = foodCategory;
	}

	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Food))
			return false;
		
		Food other = (Food) obj;
		return this.foodName.equals(other.foodName);
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return foodName.length();
	}
	
	
	@Override
	public String toString() {
		return "Food [foodId=" + foodId + ", foodCategory=" + foodCategory + ", foodName=" + foodName + ", foodPrice="
				+ foodPrice + ", foodQuantity=" + foodQuantity + ", foodSubTotal=" + foodSubTotal;
	}	
	
	
}
