package service;

import java.util.ArrayList;

import model.Line;

public interface LineService {
	public ArrayList<Line> getLineInf(int type,int deliver,int start_point);
}
