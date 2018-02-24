package dao;

import java.util.ArrayList;

import model.Point;
import model.Staff;

public interface PointDao {
	//得到中转中心列表
	public ArrayList<Point> getTransit();
	//得到中转中心下属的营业厅
	public ArrayList<Point> getDistribute(int transit_id);
	//得到营业厅列表
	public ArrayList<Point> getAllDistribute();
	//得到营业厅名称
	public String getDistributeInf(int distribute_id);
	//得到中转中心名称
	public String getTransitInf(int transit_id);
	//得到地区名称
	public String getCustomerInf(int address_id);
	//得到员工信息
	public ArrayList<Staff> getStaff_tra(int transit_id);
	//得到员工信息
	public ArrayList<Staff> getStaff_dis(int distribute_id);
	
}
