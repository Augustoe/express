package service;

import java.util.ArrayList;

import model.Point;
import model.Staff;

public interface PointService {
		//得到中转中心列表
		public ArrayList<Point> getTransit();
		//得到中转中心下属的营业厅
		public ArrayList<Point> getDistribute(int transit_id);
		//得到营业厅列表
		public ArrayList<Point> getAllDistribute();
		//得到员工信息
		public ArrayList<Staff> getStaff(String type,int id);
}
