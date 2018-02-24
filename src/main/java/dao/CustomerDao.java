package dao;

import java.util.ArrayList;

import model.Customer;

public interface CustomerDao {
	public ArrayList<Customer> getTopCustomer(String type);
}
