package service.impl;

import java.util.ArrayList;

import dao.PositionDao;
import dao.impl.PositionDaoImpl;
import model.PositionInf;
import service.PositionService;

public class PositionServiceImpl implements PositionService{
	public PositionDao position;
	
	public PositionServiceImpl(){
		position=new PositionDaoImpl();
	}
	@Override
	public ArrayList<PositionInf> getPositionInf(int point_id,String type){
		return position.getPositionInf(point_id, type);
	}
}
