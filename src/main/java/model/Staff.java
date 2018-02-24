package model;

public class Staff {
	public int staff_id;
	public String staff_type;
	public String staff_name;
	
	@Override
	public String toString() {
		return "staff_id=" + staff_id + " staff_name=" + staff_name ;
	}
}
