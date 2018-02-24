package model;

public class Line {
	public String start_point;
	public String end_point;
	public int amount;
	public int toolate;
	public int quick;
	public int borken;
	public int lose;
	public int grade;
	public int a_grade;
	public int t_grade;
	public int q_grade;
	public int b_grade;
	public int l_grade;
	public int rank;
	
	@Override
	public String toString() {
		return "start_point=" + start_point + ", end_point=" + end_point + ", amount=" + amount + ", toolate="
				+ toolate + ", quick=" + quick + ", borken=" + borken + ", lose=" + lose + ", grade=" + grade
				+ ", a_grade=" + a_grade + ", t_grade=" + t_grade + ", q_grade=" + q_grade + ", b_grade=" + b_grade
				+ ", l_grade=" + l_grade + ", rank=" + rank ;
	}
}
