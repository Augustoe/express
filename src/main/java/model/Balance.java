package model;

public class Balance {
	public int date;
	public int place;
	public int Income;
	public int cost;
	public int balance;
	
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
		return "date=" + date_str +"place"+place+ "Income=" + Income + " cost=" + cost+"balance="+balance ;
	}
}
