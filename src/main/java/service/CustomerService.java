package service;

import java.util.ArrayList;

import model.Customer;

public interface CustomerService {
	public ArrayList<Customer> getTopCustomer(String type);
}
