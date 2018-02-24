package model;

public class StaffPerform {
	public int date;
	public int count_num;
	public int lose_num;
	public int break_num;
	public int count_price;
	public int lose_price;
	public int break_price;
	
	@Override
	public String toString() {
		String date_str;
		//16
		if(date<100) date_str=Integer.toString(date);
		else if(date<10000){
		//1601
			date_str=Integer.toString(date/100)+'/'+Integer.toString(date%100);
		}else{
		//160101
			date_str=Integer.toString(date/10000)+'/'+Integer.toString((date%10000)/100)+'/'+Integer.toString(date%100);
		}
		return "date=" + date_str + " count_num=" + count_num
				+ " lose_num=" + lose_num+ " break_num=" + break_num
				+ " count_price=" + count_price+ " lose_price=" + lose_price
				+ " break_price=" + break_price;
	}
}
