package controller;

import java.util.ArrayList;

import com.google.gson.Gson;

import model.PositionInf;
import service.PositionService;
import service.impl.PositionServiceImpl;

public class PointController {
	public Gson gson;
	public PositionService position;
	public PointController(){
		gson=new Gson();
		position=new PositionServiceImpl();
	}
	
	public String getPointData(int point_id,String type){
		System.out.println(point_id+","+type);
		ArrayList<PositionInf> result=position.getPositionInf(point_id, type);
		return gson.toJson(result);
	}
}
