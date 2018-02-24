package dao;

import java.util.ArrayList;

import model.Line;

public interface LineDao {
	public ArrayList<Line> getLineInf(int type, int deliver, int start_point);
}
