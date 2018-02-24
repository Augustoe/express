package service.impl;

import java.util.ArrayList;

import dao.LineDao;
import dao.impl.LineDaoImpl;
import model.Line;
import service.LineService;

public class LineServiceImpl implements LineService{
	
	public LineDao line;
	
	public LineServiceImpl(){
		line=new LineDaoImpl();
	}
	
	@Override
	public ArrayList<Line> getLineInf(int type, int deliver, int start_point) {
		// TODO Auto-generated method stub
		return line.getLineInf(type, deliver, start_point);
	}

}
