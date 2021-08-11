package com.vrs.dao;

import java.util.List;

import com.vrs.entities.*;

public interface ICustomerRepository {

	public Customer addCustomer(Customer customer);
	public Customer removeCustomer(Customer customer);
	public Customer viewCustomer(Customer c);
	public Customer updateCustomer(Customer c);
	public List<Customer> viewAllCustomer(String vtype);
	public List<Customer> viewAllCustomersByLocation(String location);
	
}
