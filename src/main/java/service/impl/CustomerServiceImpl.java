package service.impl;

import java.util.ArrayList;

import dao.CustomerDao;
import dao.impl.CustomerDaoImpl;
import model.Customer;
import service.CustomerService;

public class CustomerServiceImpl implements CustomerService{

	public CustomerDao customer;
	
	public CustomerServiceImpl(){
		customer=new CustomerDaoImpl();
	}
	@Override
	public ArrayList<Customer> getTopCustomer(String type) {
		// TODO Auto-generated method stub
		return customer.getTopCustomer(type);
	}

}
