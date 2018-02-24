package dao;

import java.util.ArrayList;

import model.StaffPerform;

public interface StaffDao {
	//得到30天的表现
	public ArrayList<StaffPerform> getPerform_day(int staff_id);
	//得到30天的表现
	public ArrayList<StaffPerform> getPerform_month(int staff_id);
}
