package service;

import java.util.ArrayList;

import model.PositionInf;

public interface PositionService {
	public ArrayList<PositionInf> getPositionInf(int point_id,String type); 
}
