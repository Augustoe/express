package dao.impl;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import dao.PositionDao;
import model.PositionInf;

public class PositionDaoImpl implements PositionDao{
	public static final String driver = "org.gjt.mm.mysql.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/express?user=root&password=141250199&useUnicode=true&characterEncoding=utf8";
	public static Connection conn = null;
	public static PreparedStatement pstmt;
	
	@Override
	public ArrayList<PositionInf> getPositionInf(int point_id,String type){
		String table="point_"+type;
		
		ArrayList<PositionInf> list = new ArrayList<PositionInf>();
		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url);
			String sql = "select * from "+table+" where point_id="+point_id;
			System.out.println(sql);
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				PositionInf temp = new PositionInf();
				temp.date = rs.getInt("date");
				temp.point_id=point_id;
				temp.amount = rs.getInt("amount");
				temp.price = rs.getInt("price");
				temp.weight = rs.getInt("weight");
//				System.out.println(temp.date);
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
		new PositionDaoImpl().getPositionInf(1, "day");
	}
}
