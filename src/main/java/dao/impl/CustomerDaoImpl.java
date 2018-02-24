package dao.impl;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import dao.CustomerDao;
import dao.PointDao;
import model.Customer;

public class CustomerDaoImpl implements CustomerDao{
	public static final String driver = "org.gjt.mm.mysql.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/express?user=root&password=141250199&useUnicode=true&characterEncoding=utf8";
	public static Connection conn = null;
	public static PreparedStatement pstmt;
	
	public PointDao point;
	
	public CustomerDaoImpl(){
		point=new PointDaoImpl();
	}
	
	@Override
	public ArrayList<Customer> getTopCustomer(String type) {
		String key="";
		switch(type){
		case "amount": key="amount"; break;
		case "price": key="sum_price"; break;
		case "weight": key="sum_weight"; break;
		case "distance": key="distance"; break;
		default: key="amount"; break;
		}
		
		ArrayList<Customer> list = new ArrayList<Customer>();
		ArrayList<Integer> id=new ArrayList<Integer>();
		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url);
			String sql = "select customer_id,"+key+" from customer_m order by "+key+" desc limit 10;";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Customer temp = new Customer();
				id.add( rs.getInt("customer_id"));
				temp.attr = rs.getInt(key);
				list.add(temp);
			}
			for(int i=0;i<10;i++){
				list.get(i).customer_name=point.getCustomerInf(id.get(i));
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
		new CustomerDaoImpl().getTopCustomer("price");
	}

}
