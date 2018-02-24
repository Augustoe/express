package Pretreat;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.jdt.internal.compiler.batch.Main;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import model.Point;

/*用来综合两张表
use express;
create table `customer_m` as
(select dis.customer_id as customer_id, dis.month as date, (dis.amount+tra.amount) as amount,
(dis.sum_price+tra.sum_price) as sum_price,(dis.sum_weight+tra.sum_weight) as sum_weight,
(dis.sum_distance+tra.sum_distance1+tra.sum_distance2+tra.sum_distance3) as distance
 from customer_m_dis dis,customer_m_tra tra where dis.month=1706 and tra.month=1706 and 
dis.customer_id=tra.customer_id);

insert into  `customer_m`(customer_id,date,amount,sum_price,sum_weight,distance) 
(select customer_id,month,amount,sum_price,sum_weight,(sum_distance1+sum_distance2+sum_distance3)
 from customer_m_tra where month=1706 and customer_id not in
 (select customer_id from customer_m_dis where month=1706));

*/

//pre-treat the customer data
//最小单位设置为日  只考虑寄件人，不考虑收件人
public class Customer_treat {
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
			String sql = "drop table if exists `customer_tra`";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.executeUpdate();
			//create
			sql = "create table `customer_tra`("
			+"`customer_id` int(7) not null,"
			+"`date` int(6) not null,"
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
			sql ="insert into customer_tra "+
			"SELECT address1*10000+road1*100+num1 as customer_id,transit_form_id div 10000 as day,count(*),sum(price),sum(weight),sum(distance1),sum(distance2),sum(distance3) FROM transit_form group by  transit_form_id div 10000,address1,road1,num1;";
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
			String sql = "drop table if exists `customer_dis`";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.executeUpdate();
			//create
			sql = "create table `customer_dis`("
			+"`customer_id` int(7) not null,"
			+"`date` int(6) not null,"
			+"`amount` int(6) not null,"//总数
			+"`sum_price` int(10) not null,"//总额
			+"`sum_weight` int(10) not null,"//总重
			+"`sum_distance` int(10) not null)"//总距离
			+"DEFAULT CHARSET=utf8;";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.executeUpdate();
			//insert
			sql ="insert into customer_dis "+
			"SELECT address1*10000+road1*100+num1 as customer_id,distribute_form_id div 10000 as day,count(*),sum(price),sum(weight),sum(distance1) FROM distribute_form group by  distribute_form_id div 10000,address1,road1,num1;";
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
	
	public static void intergrate_day(){
		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url);
			//drop
			String sql = "use express;"+
					"create table `customer_m` as"+
					"(select dis.customer_id as customer_id, dis.month as date, (dis.amount+tra.amount) as amount,"+
					"(dis.sum_price+tra.sum_price) as sum_price,(dis.sum_weight+tra.sum_weight) as sum_weight,"+
					"(dis.sum_distance+tra.sum_distance1+tra.sum_distance2+tra.sum_distance3) as distance"+
					" from customer_m_dis dis,customer_m_tra tra where dis.month=1706 and tra.month=1706 and "+
					"dis.customer_id=tra.customer_id);"+
					"insert into  `customer_m`(customer_id,date,amount,sum_price,sum_weight,distance) "+
					"(select customer_id,month,amount,sum_price,sum_weight,(sum_distance1+sum_distance2+sum_distance3)"+
					" from customer_m_tra where month=1706 and customer_id not in"+
					" (select customer_id from customer_m_dis where month=1706));";
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
//		Customer_treat.createTable_dis();
//		Customer_treat.createTable_tra();
	}
	
}
