package dao.impl;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import dao.FinanceDao;
import model.Balance;
import model.Point;

public class FinanceDaoImpl implements FinanceDao{
	public static final String driver = "org.gjt.mm.mysql.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/express?user=root&password=141250199&useUnicode=true&characterEncoding=utf8";
	public static Connection conn = null;
	public static PreparedStatement pstmt;
	
	@Override
	public ArrayList<Balance> getThisYear() {
			ArrayList<Balance> list = new ArrayList<Balance>();
			try {
				Class.forName(driver);
				conn = (Connection) DriverManager.getConnection(url);
				String sql = "select * from balance where place=0 and date>=1701";
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					Balance temp = new Balance();
					temp.date = rs.getInt("date");
					temp.place = rs.getInt("place");
					temp.Income = rs.getInt("income");
					temp.cost = rs.getInt("cost");
					temp.balance = rs.getInt("balance");
//					System.out.println(temp.date);
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
	public ArrayList<Balance> getLastYear() {
		ArrayList<Balance> list = new ArrayList<Balance>();
		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url);
			String sql = "select * from balance where place=0 and date<1701";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Balance temp = new Balance();
				temp.date = rs.getInt("date");
				temp.place = rs.getInt("place");
				temp.Income = rs.getInt("income");
				temp.cost = rs.getInt("cost");
				temp.balance = rs.getInt("balance");
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
		return list;
}

	@Override
	public ArrayList<Balance> getDomain() {
		ArrayList<Balance> list = new ArrayList<Balance>();
		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url);
			String sql = "select * from balance where place!=0 and date=1706";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Balance temp = new Balance();
				temp.date = rs.getInt("date");
				temp.place = rs.getInt("place");
				temp.Income = rs.getInt("income");
				temp.cost = rs.getInt("cost");
				temp.balance = rs.getInt("balance");
//				System.out.println(temp.place);
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

	public static void main(String[] args) {
		FinanceDaoImpl here=new FinanceDaoImpl();
		here.getThisYear();
//		System.out.println("AAAAAAAAAAAAAAAA");
		here.getLastYear();
//		System.out.println("BBBBBBBBBBBBBBBB");
		here.getDomain();
	}
}
