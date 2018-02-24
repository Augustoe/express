package Pretreat.staff;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import tool.TimeHelper;

public class cardriver {
	public static final String driver = "org.gjt.mm.mysql.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/express?user=root&password=141250199&useUnicode=true&characterEncoding=utf8";
	public static Connection conn = null;
	public static PreparedStatement pstmt;

	public static int[] cardriver = { 10104, 10105, 10204, 10205, 10304, 10305, 20104, 20105, 20204, 20205, 20304, 20305,
			30104, 30105, 30204, 30205, 30304, 30305, 40104, 40105, 40204, 40205, 40304, 40305, 50104, 50105, 50204,
			50205, 50304, 50305 };
	public static void getDay() {

		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url);
			ResultSet rs;

			String sql_aim = "insert into staff_perform values";
			for (int i = 0; i < cardriver.length; i++) {
				int staff_id = cardriver[i];
				int start_day = 170601;
				int end_day = 170601;

				while (end_day <= 170701) {
					String sql = "";
					int count_num = 0;
					int lose_num = 0;
					int break_num = 0;
					int count_price = 0;
					int lose_price = 0;
					int break_price = 0;
					start_day = end_day;
					end_day = TimeHelper.addDay(start_day, 1);

					// count
					sql = "select count(*),sum(price) from staff_transit_day where (distribute1_cid=" + staff_id
							+ ") and (start_time between " + start_day * 10000 + " and "
							+ end_day * 10000 + ");";
					pstmt = (PreparedStatement) conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					rs.next();
					if (rs.getInt("count(*)") == 0) {
						count_num = 0;
						count_price = 0;
					} else {
						count_num = rs.getInt("count(*)");
						count_price = rs.getInt("sum(price)");
					}
					sql = "select count(*),sum(price) from staff_distribute_day where (distribute1_cid=" + staff_id
							+ ") and (start_time between " + start_day * 10000 + " and "
							+ end_day * 10000 + ");";
					pstmt = (PreparedStatement) conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					rs.next();
					if (rs.getInt("count(*)") != 0) {
						count_num += rs.getInt("count(*)");
						count_price += rs.getInt("sum(price)");
					}
					// lose
					sql = "select count(*),sum(price) from staff_transit_day where lost=" + staff_id
							+ " and (start_time between " + start_day * 10000 + " and " + end_day * 10000 + ");";
					pstmt = (PreparedStatement) conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					rs.next();
					if (rs.getInt("count(*)") == 0) {
						lose_num = 0;
						lose_price = 0;
					} else {
						lose_num = rs.getInt("count(*)");
						lose_price = rs.getInt("sum(price)");
					}
					sql = "select count(*),sum(price) from staff_distribute_day where lost=" + staff_id
							+ " and (start_time between " + start_day * 10000 + " and " + end_day * 10000 + ");";
					pstmt = (PreparedStatement) conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					rs.next();
					if (rs.getInt("count(*)") != 0) {
						count_num += rs.getInt("count(*)");
						count_price += rs.getInt("sum(price)");
					}
					// break;
					sql = "select count(*),sum(price) from staff_transit_day where broken=" + staff_id
							+ " and (start_time between " + start_day * 10000 + " and " + end_day * 10000 + ");";
					pstmt = (PreparedStatement) conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					rs.next();
					if (rs.getInt("count(*)") == 0) {
						break_num = 0;
						break_price = 0;
					} else {
						break_num = rs.getInt("count(*)");
						break_price = rs.getInt("sum(price)");
					}
					sql = "select count(*),sum(price) from staff_distribute_day where broken=" + staff_id
							+ " and (start_time between " + start_day * 10000 + " and " + end_day * 10000 + ");";
					pstmt = (PreparedStatement) conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					rs.next();
					if (rs.getInt("count(*)") != 0) {
						break_num += rs.getInt("count(*)");
						break_price += rs.getInt("sum(price)");
					}
					sql_aim+="("+staff_id+","+start_day+","+count_num+","+lose_num+","+break_num+
							","+count_price+","+lose_price+","+break_price+"),";
				}
			}
			sql_aim=sql_aim.substring(0,sql_aim.length()-1)+";";
			pstmt = (PreparedStatement) conn.prepareStatement(sql_aim);
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

	public static void getMonth() {

		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url);
			ResultSet rs;

			String sql_aim = "insert into staff_perform values";
			for (int i = 0; i < cardriver.length; i++) {
				int staff_id = cardriver[i];
				int start_day = 1607;
				int end_day = 1607;

				while (end_day < 1707) {
					String sql = "";
					int count_num = 0;
					int lose_num = 0;
					int break_num = 0;
					int count_price = 0;
					int lose_price = 0;
					int break_price = 0;
					start_day = end_day;
					if(end_day==1612){
						end_day=1701;
					}else{
					end_day ++;
					}
					// count
					sql = "select count(*),sum(price) from staff_transit_month where (distribute1_cid=" + staff_id
							+ ") and (start_time between " + start_day * 1000000 + " and "
							+ end_day * 1000000 + ");";
					pstmt = (PreparedStatement) conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					rs.next();
					if (rs.getInt("count(*)") == 0) {
						count_num = 0;
						count_price = 0;
					} else {
						count_num = rs.getInt("count(*)");
						count_price = rs.getInt("sum(price)");
					}
					sql = "select count(*),sum(price) from staff_distribute_month where (distribute1_cid=" + staff_id
							+ ") and (start_time between " + start_day * 1000000 + " and "
							+ end_day * 1000000 + ");";
					pstmt = (PreparedStatement) conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					rs.next();
					if (rs.getInt("count(*)") != 0) {
						count_num += rs.getInt("count(*)");
						count_price += rs.getInt("sum(price)");
					}
					// lose
					sql = "select count(*),sum(price) from staff_transit_month where lost=" + staff_id
							+ " and (start_time between " + start_day * 1000000 + " and " + end_day * 1000000 + ");";
					pstmt = (PreparedStatement) conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					rs.next();
					if (rs.getInt("count(*)") == 0) {
						lose_num = 0;
						lose_price = 0;
					} else {
						lose_num = rs.getInt("count(*)");
						lose_price = rs.getInt("sum(price)");
					}
					sql = "select count(*),sum(price) from staff_distribute_month where lost=" + staff_id
							+ " and (start_time between " + start_day * 1000000 + " and " + end_day * 1000000 + ");";
					pstmt = (PreparedStatement) conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					rs.next();
					if (rs.getInt("count(*)") != 0) {
						count_num += rs.getInt("count(*)");
						count_price += rs.getInt("sum(price)");
					}
					// break;
					sql = "select count(*),sum(price) from staff_transit_month where broken=" + staff_id
							+ " and (start_time between " + start_day * 1000000 + " and " + end_day * 1000000 + ");";
					pstmt = (PreparedStatement) conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					rs.next();
					if (rs.getInt("count(*)") == 0) {
						break_num = 0;
						break_price = 0;
					} else {
						break_num = rs.getInt("count(*)");
						break_price = rs.getInt("sum(price)");
					}
					sql = "select count(*),sum(price) from staff_distribute_month where broken=" + staff_id
							+ " and (start_time between " + start_day * 1000000 + " and " + end_day * 1000000 + ");";
					pstmt = (PreparedStatement) conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					rs.next();
					if (rs.getInt("count(*)") != 0) {
						break_num += rs.getInt("count(*)");
						break_price += rs.getInt("sum(price)");
					}
					sql_aim+="("+staff_id+","+start_day+","+count_num+","+lose_num+","+break_num+
							","+count_price+","+lose_price+","+break_price+"),";
				}
			}
			sql_aim=sql_aim.substring(0,sql_aim.length()-1)+";";
			pstmt = (PreparedStatement) conn.prepareStatement(sql_aim);
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
		cardriver here=new cardriver();
//		here.getDay();
		here.getMonth();
	}
}
