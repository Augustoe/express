package dao.impl;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import dao.LineDao;
import dao.PointDao;
import model.Line;

public class LineDaoImpl implements LineDao{
	public static final String driver = "org.gjt.mm.mysql.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/express?user=root&password=141250199&useUnicode=true&characterEncoding=utf8";
	public static Connection conn = null;
	public static PreparedStatement pstmt;
	
	public PointDao point;
	
	public LineDaoImpl(){
		point=new PointDaoImpl();
	}
	
	@Override
	public ArrayList<Line> getLineInf(int type, int deliver, int start_point) {
		// TODO Auto-generated method stub
		String symbol1=">";
		String symbol2=">";
		if(type>2) symbol1="<";
		if(type%2==0) symbol2="<";
		
		ArrayList<Line> list = new ArrayList<Line>();
		ArrayList<Integer> start=new ArrayList<Integer>();
		ArrayList<Integer> end=new ArrayList<Integer>();
		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url);
			String sql = "select * from line_day where start_point="+start_point+" and end_point"+symbol2+"100 and type="+deliver;
			System.out.println(sql);
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Line temp = new Line();
				start.add(rs.getInt("start_point"));
				end.add(rs.getInt("end_point"));
				
				temp.amount = rs.getInt("amount");
				temp.a_grade = rs.getInt("a_grade");
				
				temp.b_grade = rs.getInt("b_grade");
				temp.borken = rs.getInt("break");
				
				temp.grade = rs.getInt("grade");
				
				temp.l_grade=rs.getInt("l_grade");
				temp.lose=rs.getInt("lose");
				
				temp.t_grade=rs.getInt("t_grade");
				temp.toolate=rs.getInt("toolate");
				
				temp.q_grade=rs.getInt("q_grade");
				temp.quick=rs.getInt("quick");
				list.add(temp);
			}
			System.out.println("得到条目："+list.size());
			for(int i=0;i<start.size();i++){
				if(start.get(i)>100){
					list.get(i).start_point=point.getDistributeInf(start.get(i));
				}else{
					list.get(i).start_point=point.getTransitInf(start.get(i));
				}
				if(end.get(i)>100){
					list.get(i).end_point=point.getDistributeInf(end.get(i));
				}else{
					list.get(i).end_point=point.getTransitInf(end.get(i));
				}
				//得到排名
				sql = "select count(*) as rank from line_day where start_point"+symbol1+"100 and end_point"+symbol2+"100 and type="+deliver
						+" and grade>"+list.get(i).grade;
				System.out.println(sql);
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				rs.next();
				list.get(i).rank=rs.getInt("rank")+1;
				System.out.println(list.get(i).rank);
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
		new LineDaoImpl().getLineInf(2,1,203);
	}

}
