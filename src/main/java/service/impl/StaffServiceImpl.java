package service.impl;

import java.util.ArrayList;

import dao.StaffDao;
import dao.impl.StaffDaoImpl;
import model.StaffPerform;
import service.StaffService;

public class StaffServiceImpl implements StaffService{
	private StaffDao staff;
	
	public StaffServiceImpl(){
		staff=new StaffDaoImpl();
	}
	@Override
	public ArrayList<StaffPerform> getStaffPerform(String type, int staff_id) {
		// TODO Auto-generated method stub
		if(type.equals("day")) return staff.getPerform_day(staff_id);
		else return staff.getPerform_month(staff_id);
		
	}

}
