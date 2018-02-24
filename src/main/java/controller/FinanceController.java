package controller;

import java.util.ArrayList;

import com.google.gson.Gson;

import model.Balance;
import service.FinanceService;
import service.impl.FinanceServiceImpl;

public class FinanceController {
	public FinanceService finance;
	public Gson gson;
	
	public FinanceController(){
		gson=new Gson();
		finance=new FinanceServiceImpl();
	}
	
	public String getMonth(){
		ArrayList<Balance> result=finance.getThisYear();
		result.addAll(finance.getLastYear());
		return gson.toJson(result);
	}
	
	public String getDomain(){
		ArrayList<Balance> result=finance.getDomain();
		return gson.toJson(result);
	}
}
