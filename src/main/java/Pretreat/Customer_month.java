package Pretreat;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Customer_month {
	public static final String driver = "org.gjt.mm.mysql.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/express?user=root&password=141250199&useUnicode=true&characterEncoding=utf8";
	public static Connection conn = null;
	public static PreparedStatement pstmt;
	//transit
	public static void createTable_tra(){
		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url);
			//drop
			String sql = "drop table if exists `customer_m_tra`";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.executeUpdate();
			//create
			sql = "create table `customer_m_tra`("
			+"`customer_id` int(7) not null,"
			+"`month` int(4) not null,"
			+"`amount` int(6) not null,"//总数
			+"`sum_price` int(10) not null,"//总额
			+"`sum_weight` int(10) not null,"//总重 
			+"`sum_distance1` int(10) not null,"//总距离1
			+"`sum_distance2` int(10) not null,"//总距离2
			+"`sum_distance3` int(10) not null)"//总距离3
			+"DEFAULT CHARSET=utf8;";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.executeUpdate();
			//insert
			sql ="insert into customer_m_tra "+
			"SELECT customer_id,date div 100 as month,sum(amount),sum(sum_price),sum(sum_weight),sum(sum_distance1),sum(sum_distance2),sum(sum_distance3) FROM customer_tra group by date div 100,customer_id;";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.executeUpdate();
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
	}
	//distribute
	public static void createTable_dis(){
		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url);
			//drop
			String sql = "drop table if exists `customer_m_dis`";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.executeUpdate();
			//create
			sql = "create table `customer_m_dis`("
			+"`customer_id` int(7) not null,"
			+"`month` int(4) not null,"
			+"`amount` int(6) not null,"//总数
			+"`sum_price` int(10) not null,"//总额
			+"`sum_weight` int(10) not null,"//总重
			+"`sum_distance` int(10) not null)"//总距离
			+"DEFAULT CHARSET=utf8;";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.executeUpdate();
			//insert
			sql ="insert into customer_m_dis "+
			"SELECT customer_id,date div 100 as month,sum(amount),sum(sum_price),sum(sum_weight),sum(sum_distance) FROM customer_dis group by date div 100,customer_id;";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.executeUpdate();
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
	}
	
	public static void main(String[] args) {
		Customer_month.createTable_dis();
		Customer_month.createTable_tra();
	}
	
}
