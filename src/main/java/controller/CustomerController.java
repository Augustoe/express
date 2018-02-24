package controller;

import java.util.ArrayList;
import java.util.Collections;

import com.google.gson.Gson;

import model.Customer;
import service.CustomerService;
import service.impl.CustomerServiceImpl;

public class CustomerController {
	public CustomerService customer;
	public Gson gson;
	
	public CustomerController(){
		gson=new Gson();
		customer=new CustomerServiceImpl();
	}
	
	public String getTopCustomer(String type){
		ArrayList<Customer> result=customer.getTopCustomer(type);
		Collections.reverse(result);
		return gson.toJson(result);
	}
}
