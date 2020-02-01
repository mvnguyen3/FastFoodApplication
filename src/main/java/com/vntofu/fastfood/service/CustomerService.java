package com.vntofu.fastfood.service;

import java.util.List;

import com.vntofu.fastfood.domain.Customer;

public interface CustomerService {
	boolean saveCustomer(Customer customer);
	List<Customer> findAllCustomers();
	Customer findCustomerById(long id);
	Customer deleteCustomerById(long id);
}
