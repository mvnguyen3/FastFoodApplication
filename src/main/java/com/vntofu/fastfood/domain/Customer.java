package com.vntofu.fastfood.domain;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;


@Entity(name = "Customer")
public class Customer {
	@Id
	@Column(name="Customer_Id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long customerId;
	private String customerName;
	private String customerPhone;
	private String sessionCode;
	private double total;
	
	@JsonBackReference	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Cart cart;

	public Customer() {}
	
	public Customer(String customerName, String customerPhone, Cart cart) {
		super();
		this.customerName = customerName;
		this.customerPhone = customerPhone;
		this.cart = cart;
	}

	public long getCustomerId() {
		return customerId;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	public String getSessionCode() {
		return sessionCode;
	}

	public void setSessionCode(String sessionCode) {
		this.sessionCode = sessionCode;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerPhone="
				+ customerPhone + ", total=" + total;
	}			

}
