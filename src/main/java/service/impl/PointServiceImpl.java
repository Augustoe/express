package service.impl;

import java.util.ArrayList;

import dao.PointDao;
import dao.impl.PointDaoImpl;
import model.Point;
import model.Staff;
import service.PointService;

public class PointServiceImpl implements PointService{

	private PointDao point;
	
	public PointServiceImpl(){
		point=new PointDaoImpl();
	}
	
	
	@Override
	public ArrayList<Point> getTransit() {
		// TODO Auto-generated method stub
		return point.getTransit() ;
	}

	@Override
	public ArrayList<Point> getDistribute(int transit_id) {
		// TODO Auto-generated method stub
		return point.getDistribute(transit_id) ;
	}

	@Override
	public ArrayList<Point> getAllDistribute() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Staff> getStaff(String type,int id) {
		// TODO Auto-generated method stub
		ArrayList<Staff> list;
		if(id<6){
			list= point.getStaff_tra(id);
		}else{
			list= point.getStaff_dis(id);
		}
		System.out.println("KKKKKKKKKKKKKK"+list.size());
		String key="";
		switch(type){
		case "poster": key+='P'; break;
		case "distribute": key+='T'; break;
		case "transit": key+='T'; break;
		case "storehouse": key+='S'; break;
		case "driver": key+='C'; break;
		default:  break;
		}
		for(int i=list.size()-1;i>=0;i--){
			if(!list.get(i).staff_type.equals(key)) list.remove(i);
		}
		System.out.println("PPPPPPPPPP"+list.size());
		return list;
	}
	
	public static void main(String[] args) {
	new PointServiceImpl().getStaff("poster",201);
	}

}
