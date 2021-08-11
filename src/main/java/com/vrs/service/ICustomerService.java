package com.vrs.service;

import com.vrs.entities.*;
import java.util.List;


public interface ICustomerService {

	public Customer addCustomer(Customer customer);
	public Customer removeCustomer(Customer customer);
	public Customer viewCustomer(Customer c);
	public Customer updateCustomer(Customer c);
	public List<Customer> viewAllCustomer(String vtype);
	public List<Customer> viewAllCustomersByLocation(String location);
	
}
