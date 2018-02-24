package dao;

import java.util.ArrayList;

import model.PositionInf;

public interface PositionDao {
	public ArrayList<PositionInf> getPositionInf(int point_id,String type);
}
