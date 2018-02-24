package controller;

import java.util.ArrayList;

import com.google.gson.Gson;

import model.Point;
import model.Staff;
import service.PointService;
import service.impl.PointServiceImpl;


public class PositionController {
	
	public PointService point;
	public Gson gson;
	
	
	public PositionController(){
		point=new PointServiceImpl();
		gson = new Gson(); 
	}
	
	public String getCity(){
		ArrayList<Point> result=point.getTransit();
		return gson.toJson(result);
//		return result;
	}
	
	public String getDistribute(int tid){
		System.out.println(tid);
		ArrayList<Point> result=point.getDistribute(tid);
		return gson.toJson(result);
	}
	
	public String getStaff(String type,int tid){
		System.out.println(tid);
		ArrayList<Staff> result=point.getStaff(type,tid);
		return gson.toJson(result);
	}
	
}
