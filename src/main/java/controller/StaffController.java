package controller;

import java.util.ArrayList;

import com.google.gson.Gson;

import model.StaffPerform;
import service.StaffService;
import service.impl.StaffServiceImpl;

public class StaffController {
	public StaffService staff;
	public Gson gson;
	
	public StaffController(){
		staff=new StaffServiceImpl();
		gson=new Gson();
	}
	
	public String getStaffPerform(String type,int staff_id){
		ArrayList<StaffPerform> list=staff.getStaffPerform(type, staff_id);
		return gson.toJson(list);
	}
}
