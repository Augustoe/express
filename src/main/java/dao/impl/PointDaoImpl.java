package dao.impl;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import dao.PointDao;
import model.Point;
import model.Staff;
import tool.NameDataHelper;

public class PointDaoImpl implements PointDao {
	public static final String driver = "org.gjt.mm.mysql.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/express?user=root&password=141250199&useUnicode=true&characterEncoding=utf8";
	public static Connection conn = null;
	public static PreparedStatement pstmt;

	@Override
	public ArrayList<Point> getTransit() {
		ArrayList<Point> list = new ArrayList<Point>();
		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url);
			String sql = "select * from transit_point";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Point temp = new Point();
				temp.point_id = rs.getInt("transit_id");
				temp.point_name = rs.getString("transit_name");
				System.out.println(temp.point_name);
				list.add(temp);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					throw new RuntimeException("Exception:" + e.getMessage());
				}
			}
		}
		return list;
	}

	@Override
	public ArrayList<Point> getDistribute(int transit_id) {
		ArrayList<Point> list = new ArrayList<Point>();
		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url);
			String sql = "select * from distribute_point where distribute_id<" + (transit_id + 1) * 100
					+ " and distribute_id>" + transit_id * 100;
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Point temp = new Point();
				temp.point_id = rs.getInt("distribute_id");
				temp.point_name = rs.getString("distribute_name");
				System.out.println(temp.point_name);
				list.add(temp);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					throw new RuntimeException("Exception:" + e.getMessage());
				}
			}
		}
		return list;
	}

	@Override
	public ArrayList<Point> getAllDistribute() {
		ArrayList<Point> list = new ArrayList<Point>();
		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url);
			String sql = "select * from distribute_point";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Point temp = new Point();
				temp.point_id = rs.getInt("distribute_id");
				temp.point_name = rs.getString("distribute_name");
				System.out.println(temp.point_name);
				list.add(temp);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					throw new RuntimeException("Exception:" + e.getMessage());
				}
			}
		}
		return list;
	}

	@Override
	public ArrayList<Staff> getStaff_tra(int transit_id) {
		ArrayList<Staff> list=new ArrayList<Staff>();
		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url);
			String sql = "select * from staff where staff_id>"+transit_id*10000
					+" and staff_id<"+(transit_id*10000+7);
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Staff temp = new Staff();
				temp.staff_id = rs.getInt("staff_id");
				temp.staff_type = rs.getString("type");
				temp.staff_name= new NameDataHelper().getName();
				System.out.println(temp.staff_id+" "+temp.staff_name+" "+temp.staff_type);
				list.add(temp);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					throw new RuntimeException("Exception:" + e.getMessage());
				}
			}
		}
		return list;
	}

	@Override
	public ArrayList<Staff> getStaff_dis(int distribute_id) {

		ArrayList<Staff> list=new ArrayList<Staff>();
		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url);
			String sql = "select * from staff where staff_id>"+distribute_id*100
					+" and staff_id<"+(distribute_id*100+7);
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Staff temp = new Staff();
				temp.staff_id = rs.getInt("staff_id");
				temp.staff_type = rs.getString("type");
				temp.staff_name= new NameDataHelper().getName();
				System.out.println(temp.staff_id+" "+temp.staff_name+" "+temp.staff_type);
				list.add(temp);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					throw new RuntimeException("Exception:" + e.getMessage());
				}
			}
		}
		return list;
	
	}

	@Override
	public String getDistributeInf(int distribute_id) {
		int city=distribute_id/100;
		String dis_name="";
		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url);
			String sql = "select * from transit_point where transit_id="+city;
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			dis_name += rs.getString("transit_name");
			//dis
			sql = "select * from distribute_point where distribute_id="+distribute_id;
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			dis_name += rs.getString("distribute_name");
			dis_name += "营业厅";
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					throw new RuntimeException("Exception:" + e.getMessage());
				}
			}
		}
		return dis_name;
	}
	
	@Override
	public String getTransitInf(int transit_id) {
		String tra_name="";
		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url);
			String sql = "select * from transit_point where transit_id="+transit_id;
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			tra_name += rs.getString("transit_name");
			tra_name += "中转中心";
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					throw new RuntimeException("Exception:" + e.getMessage());
				}
			}
		}
		return tra_name;
	}

	@Override
	public String getCustomerInf(int address_id) {
		int city=address_id/1000000;
		int distribute=address_id/10000;
		int road=address_id/100;
		int num=address_id%100;
		String add_name="";
		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url);
			String sql = "select * from transit_point where transit_id="+city;
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			add_name += rs.getString("transit_name");
			//dis
			sql = "select * from distribute_point where distribute_id="+distribute;
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			add_name += rs.getString("distribute_name");
			//road
			sql = "select * from road where road_id="+road;
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			add_name += rs.getString("road_name");
			add_name+= Integer.toString(num)+"号";
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					throw new RuntimeException("Exception:" + e.getMessage());
				}
			}
		}
		return add_name;
	}

	public static void main(String[] args) {
		PointDao here = new PointDaoImpl();
		// here.getTransit();
//		here.getDistribute(1);
//		here.getAllDistribute();
//		here.getStaff_dis(102);
//		here.getStaff_tra(1);
//		System.out.println(here.getDistributeInf(301));
//		System.out.println(here.getCustomerInf(1010101));
	}

}
