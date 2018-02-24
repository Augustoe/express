package Pretreat;

import Pretreat.staff.StaffCreate;
import Pretreat.staff.cardriver;
import Pretreat.staff.courier;
import Pretreat.staff.distributer;
import Pretreat.staff.store;
import Pretreat.staff.transiter;

public class Main {
	public static void main(String[] args) {
		StaffCreate staff=new StaffCreate();
		staff.createTable();
		staff.createTempTable();
		staff.createTempTable_month();
		new courier().getMonth();
		new distributer().getMonth();
		new store().getMonth();
		new transiter().getMonth();
		new cardriver().getMonth();
		
		Customer_treat.createTable_dis();
		Customer_treat.createTable_tra();
		Customer_treat.intergrate_day();
		
		new Position_treat().position_treat();
		new Line_treat().line_create();
		
		
	}
}
