package service;

import java.util.ArrayList;

import model.StaffPerform;

public interface StaffService {
	//得到员工的表现情况
	public ArrayList<StaffPerform> getStaffPerform(String type,int staff_id);
}
