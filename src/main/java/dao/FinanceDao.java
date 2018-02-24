package dao;

import java.util.ArrayList;

import model.Balance;

public interface FinanceDao {
	//今年的月数据
	public ArrayList<Balance> getThisYear();
	//去年的月数据
	public ArrayList<Balance> getLastYear();
	//地区的月数据
	public ArrayList<Balance> getDomain();
}
