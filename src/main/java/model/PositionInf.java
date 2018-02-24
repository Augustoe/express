package model;

public class PositionInf {
	public int point_id;
	public int date;
	public int amount;
	public int price;
	public int weight;
	
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
		return "date=" + date_str +"point_id"+point_id+ "amount=" + amount + " price=" + price+" weight="+weight ;
	}
}
