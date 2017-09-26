package com.mike.service;

import java.util.List;

import com.mike.bean.Customer;

public interface CustomerService {
	 public Customer addCustomer(Customer customer);	
	 
	 public void updateCustomer(String userID,List<Customer> list);
	 
	 public void deleteCustomer(String id);
}
