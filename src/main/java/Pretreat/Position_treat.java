package Pretreat;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Position_treat {
	public static final String driver = "org.gjt.mm.mysql.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/express?user=root&password=141250199&useUnicode=true&characterEncoding=utf8";
	public static Connection conn = null;
	public static PreparedStatement pstmt;

	public static String sql_day;
	public static String sql_month;

	public Position_treat() {
		sql_day = "drop table if exists `point_temp_day`;" + "create table `point_temp_day`(" + "`point_id` int(4),"
				+ "`date` int(6)," + "`amount` int(8)," + "`price` int(10)," + "`weight` int(8)"
				+ ")DEFAULT CHARSET=utf8;" +

				"insert into `point_temp_day`"
				+ "(select address1,start_time div 10000,count(*),sum(price),sum(weight) from staff_transit_day group by address1,start_time div 10000);"
				+ "insert into `point_temp_day`"
				+ "(select address2,start_time div 10000,count(*),sum(price),sum(weight) from staff_transit_day group by address2,start_time div 10000);"
				+ "insert into `point_temp_day`"
				+ "(select address1,start_time div 10000,count(*),sum(price),sum(weight) from staff_distribute_day group by address1,start_time div 10000);"
				+ "insert into `point_temp_day`"
				+ "(select address2,start_time div 10000,count(*),sum(price),sum(weight) from staff_distribute_day group by address2,start_time div 10000);"
				+ "insert into `point_temp_day`"
				+ "(select tansit1,start_time div 10000,count(*),sum(price),sum(weight) from staff_transit_day group by tansit1,start_time div 10000);"
				+ "insert into `point_temp_day`"
				+ "(select tansit2,start_time div 10000,count(*),sum(price),sum(weight) from staff_transit_day group by tansit2,start_time div 10000);"
				+ "drop table if exists `point_day`;" + "create table `point_day` as"
				+ "(select point_id,date,sum(amount) as amount,sum(price) as price ,sum(weight) as weight from point_temp_day group by point_id,date);"
				+ "drop table if exists `point_temp_day`;";
		sql_month = "use express ;" + "drop table if exists `point_temp_month`;" + "create table `point_temp_month`("
				+ "`point_id` int(4)," + "`date` int(4)," + "`amount` int(8)," + "`price` int(10)," + "`weight` int(8)"
				+ ")DEFAULT CHARSET=utf8;" +

				"insert into `point_temp_month`"
				+ "(select address1,start_time div 1000000,count(*),sum(price),sum(weight) from staff_distribute_month group by address1,start_time div 1000000);"
				+ "insert into `point_temp_month`"
				+ "(select address2,start_time div 1000000,count(*),sum(price),sum(weight) from staff_distribute_month group by address2,start_time div 1000000);"
				+

				"insert into `point_temp_month`"
				+ "(select address1,start_time div 1000000,count(*),sum(price),sum(weight) from staff_transit_month group by address1,start_time div 1000000);"
				+ "insert into `point_temp_month`"
				+ "(select address2,start_time div 1000000,count(*),sum(price),sum(weight) from staff_transit_month group by address2,start_time div 1000000);"
				+

				"insert into `point_temp_month`"
				+ "(select tansit1,start_time div 1000000,count(*),sum(price),sum(weight) from staff_transit_month group by tansit1,start_time div 1000000);"
				+ "insert into `point_temp_month`"
				+ "(select tansit2,start_time div 1000000,count(*),sum(price),sum(weight) from staff_transit_month group by tansit2,start_time div 1000000);"
				+

				"drop table if exists `point_month`;" + "create table `point_month` as"
				+ "(select point_id,date,sum(amount) as amount,sum(price) as price ,sum(weight) as weight from point_temp_month group by point_id,date);"
				+

				"drop table if exists `point_temp_month`;";

	}
	
	public static void position_treat(){

		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url);
			//drop
			String sql = sql_day;
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.executeUpdate();
			sql = sql_month;
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
}
