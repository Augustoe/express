package tool;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeHelper {
	public static int addTime(int start_time,int passtime){
		int result=0;
		//start_time:16/12/24 12:42
		try{
		String start=Integer.toString(start_time);
		start="20"+start;
		Calendar day_c = new GregorianCalendar();
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
		Date days_d = df.parse(start);    
		day_c.setTime(days_d);
		day_c.add(Calendar.MINUTE, passtime);
		String end = df.format(day_c.getTime());
		end=end.substring(2);
		result=Integer.parseInt(end);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			return result;
		}
	}
	public static int addDay(int start_time,int days){
		int result=0;
		//start_time:16/12/24
		try{
			String start=Integer.toString(start_time);
			start="20"+start;
			Calendar day_c = new GregorianCalendar();
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			Date days_d = df.parse(start);    
			day_c.setTime(days_d);
			day_c.add(Calendar.DATE, days);
			String end = df.format(day_c.getTime());
			end=end.substring(2);
			result=Integer.parseInt(end);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				return result;
			}
	}
}
