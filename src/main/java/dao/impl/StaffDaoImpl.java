package dao.impl;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import dao.StaffDao;
import model.Point;
import model.StaffPerform;

public class StaffDaoImpl implements StaffDao{
	public static final String driver = "org.gjt.mm.mysql.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/express?user=root&password=141250199&useUnicode=true&characterEncoding=utf8";
	public static Connection conn = null;
	public static PreparedStatement pstmt;
	
	@Override
	public ArrayList<StaffPerform> getPerform_day(int staff_id) {
		ArrayList<StaffPerform> list = new ArrayList<StaffPerform>();
		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url);
			String sql = "select * from staff_perform where staff_id="+staff_id+" and (date>=170601 and date<170701)";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				StaffPerform temp = new StaffPerform();
				temp.date = rs.getInt("date");
				temp.count_num = rs.getInt("count_num");
				temp.lose_num= rs.getInt("lose_num");
				temp.break_num = rs.getInt("break_num");
				temp.count_price = rs.getInt("count_price");
				temp.lose_price = rs.getInt("lose_price");
				temp.break_price = rs.getInt("break_price");
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
	public ArrayList<StaffPerform> getPerform_month(int staff_id) {
		ArrayList<StaffPerform> list = new ArrayList<StaffPerform>();
		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url);
			String sql = "select * from staff_perform where staff_id="+staff_id+" and (date>=1606 and date<1707)";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				StaffPerform temp = new StaffPerform();
				temp.date = rs.getInt("date");
				temp.count_num = rs.getInt("count_num");
				temp.lose_num= rs.getInt("lose_num");
				temp.break_num = rs.getInt("break_num");
				temp.count_price = rs.getInt("count_price");
				temp.lose_price = rs.getInt("lose_price");
				temp.break_price = rs.getInt("break_price");
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
		System.out.println(list.size());
		return list;
	}
	
	public static void main(String[] args) {
		System.out.println(new StaffDaoImpl().getPerform_day(10101).size());
	}

}
