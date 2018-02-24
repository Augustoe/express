package controller;

import java.util.ArrayList;

import com.google.gson.Gson;

import model.Line;
import service.LineService;
import service.impl.LineServiceImpl;

public class LineController {
	public Gson gson;
	public LineService line;
	public LineController(){
		gson=new Gson();
		line=new LineServiceImpl();
	}
	
	public String getLineData(int type,int deliver,int start_point){
		System.out.println("here!!!!!!!!!!"+type+","+deliver+","+start_point);
		ArrayList<Line> result=line.getLineInf(type, deliver, start_point);
		return gson.toJson(result);
	}
}
