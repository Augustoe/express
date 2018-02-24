package Pretreat;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Line_treat {
	public static final String driver = "org.gjt.mm.mysql.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/express?user=root&password=141250199&useUnicode=true&characterEncoding=utf8";
	public static Connection conn = null;
	public static PreparedStatement pstmt;

	public static String sql1;
	public static String sql2;
	public static String sql3;

	public Line_treat() {
		sql1 = "drop table if exists `line_day`;"
				+ "create table `line_day`("
				+ "`start_point` int(4),"
				+ "`end_point` int(4),"
				+ "`type` int(1),"
				+ "`amount` int(8),"
				+ "`toolate` int(8),"
				+ "`quick` int(8),"
				+ "`break` int(8),"
				+ "`lose` int(8)"
				+ ")default charset=utf8;"
				+ "insert into `point_temp_month`"
				+ "(select address1,start_time div 1000000,count(*),sum(price),sum(weight) from staff_distribute_month group by address1,start_time div 1000000);insert into `point_temp_month`(select address2,start_time div 1000000,count(*),sum(price),sum(weight) from staff_distribute_month group by address2,start_time div 1000000);"
				+ "insert into `point_temp_month`"
				+ "(select address1,start_time div 1000000,count(*),sum(price),sum(weight) from staff_transit_month group by address1,start_time div 1000000);insert into `point_temp_month`(select address2,start_time div 1000000,count(*),sum(price),sum(weight) from staff_transit_month group by address2,start_time div 1000000);"
				+ "insert into `point_temp_month`"
				+ "(select tansit1,start_time div 1000000,count(*),sum(price),sum(weight) from staff_transit_month group by tansit1,start_time div 1000000);insert into `point_temp_month`(select tansit2,start_time div 1000000,count(*),sum(price),sum(weight) from staff_transit_month group by tansit2,start_time div 1000000);"
				+ "drop table if exists `point_month`;"
				+ "create table `point_month` as(select point_id,date,sum(amount) as amount,sum(price) as price ,sum(weight) as weight from point_temp_month group by point_id,date);"
				+ "drop table if exists `point_temp_month`;";
		sql2 = "alter table `line_day` add (grade float default 0);"
				+ "alter table `line_day` add (a_grade float default 0);"
				+ "alter table `line_day` add (b_grade float default 0);"
				+ "alter table `line_day` add (q_grade float default 0);"
				+ "alter table `line_day` add (t_grade float default 0);"
				+ "alter table `line_day` add (l_grade float default 0);";
		sql3 = "update line_day set "
				+ "grade=((case when toolate<amount*0.05 then 1 when toolate<amount*0.1 then 0.8when toolate<amount*0.25 then 0.6 when toolate<amount*0.5 then 0.4 else 0.2 end)*20+(case when amount>3000 then 1 when amount>800 then 0.9 else 0.8 end)*20+(case when quick>amount*0.5 then 1 when quick>amount*0.25 then 0.9 when quick>amount*0.1 then 0.8 else 0.7 end)*20+(case when lose=0 then 1 when lose=1 then 0.9when lose>1 then 0.7 else 1 end)*20+(case when break=0 then 1 when break=1 then 0.9when break>1 then 0.7 else 1 end)*20) ,"
				+ "t_grade=((case when toolate<amount*0.05 then 1 when toolate<amount*0.1 then 0.8when toolate<amount*0.25 then 0.6 when toolate<amount*0.5 then 0.4 else 0.2 end)*20),"
				+ "a_grade=((case when amount>3000 then 1 when amount>800 then 0.9 else 0.8 end)*20),"
				+ "q_grade=((case when quick>amount*0.5 then 1 when quick>amount*0.25 then 0.9 when quick>amount*0.1 then 0.8 else 0.7 end)*20),"
				+ "l_grade=((case when lose=0 then 1 when lose=1 then 0.9when lose>1 then 0.7 else 1 end)*20),"
				+ "b_grade=((case when break=0 then 1 when break=1 then 0.9when break>1 then 0.7 else 1 end)*20);";

	}
	
	public static void line_create(){
		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url);
			String sql = sql1;
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.executeUpdate();
			sql = sql2;
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.executeUpdate();
			sql = sql3;
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
