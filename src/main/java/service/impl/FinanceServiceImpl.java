package service.impl;

import java.util.ArrayList;

import dao.FinanceDao;
import dao.impl.FinanceDaoImpl;
import model.Balance;
import service.FinanceService;

public class FinanceServiceImpl implements FinanceService{
	private FinanceDao finance;
	
	public FinanceServiceImpl(){
		finance=new FinanceDaoImpl();
	}
	
	@Override
	public ArrayList<Balance> getThisYear() {
		return finance.getThisYear();
				
	}

	@Override
	public ArrayList<Balance> getLastYear() {
		return finance.getLastYear();
	}

	@Override
	public ArrayList<Balance> getDomain() {
		return finance.getDomain();
	}

}
