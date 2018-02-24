package Pretreat.staff;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class StaffCreate {
	public static final String driver = "org.gjt.mm.mysql.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/express?user=root&password=141250199&useUnicode=true&characterEncoding=utf8";
	public static Connection conn = null;
	public static PreparedStatement pstmt;

	public static void createTable() {
		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url);
			// drop
			String sql = "drop table if exists `staff_perform`";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.executeUpdate();
			// create
			sql = "create table `staff_perform`(" + "`staff_id` int(6) not null," + "`date` int(6) not null,"
					+ "`count_num` int(6) not null,"// 总数
					+ "`lose_num` int(6) not null,"// 总遗失
					+ "`break_num` int(6) not null,"// 总破损
					+ "`count_price` int(8) not null,"// 总额
					+ "`lose_price` int(8) not null,"// 总遗失额
					+ "`break_price` int(8) not null)"// 总破损额
					+ "DEFAULT CHARSET=utf8;";
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

	public static void createTempTable() {
		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url);
			// drop
			String sql = "drop table if exists `staff_transit_day`";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.executeUpdate();
			// create transit
			sql = "create table `staff_transit_day` as select * from transit_form where start_time between 1706010000 and 1707010000";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.executeUpdate();
			// drop
			sql = "drop table if exists `staff_distribute_day`";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.executeUpdate();
			// create distribute
			sql = "create table `staff_distribute_day` as select * from distribute_form where start_time between 1706010000 and 1707010000";
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
	
	public static void createTempTable_month() {
		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url);
			// drop
			String sql = "drop table if exists `staff_transit_month`";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.executeUpdate();
			// create transit
			sql = "create table `staff_transit_month` as select * from transit_form where start_time between 1607010000 and 1707010000";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.executeUpdate();
			// drop
			sql = "drop table if exists `staff_distribute_month`";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.executeUpdate();
			// create distribute
			sql = "create table `staff_distribute_month` as select * from distribute_form where start_time between 1607010000 and 1707010000";
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
	
	public static void deleteTempTable(){
			try {
				Class.forName(driver);
				conn = (Connection) DriverManager.getConnection(url);
				// drop
				String sql = "drop table if exists `staff_transit_day`";
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				pstmt.executeUpdate();
				// drop
				sql = "drop table if exists `staff_distribute_day`";
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				pstmt.executeUpdate();
				sql = "drop table if exists `staff_transit_month`";
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				pstmt.executeUpdate();
				// drop
				sql = "drop table if exists `staff_distribute_month`";
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
		StaffCreate here=new StaffCreate();
//		here.createTable();
//		here.createTempTable();
//		here.createTempTable_month();
		new courier().getMonth();
		new distributer().getMonth();
		new store().getMonth();
		new transiter().getMonth();
		
	}
	
	
}
