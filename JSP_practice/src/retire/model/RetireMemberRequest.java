package retire.model;

import java.sql.Date;

public class RetireMemberRequest {

	String state;
	int  emp_no;
	String name_kor;
	String dept;
	String job;
	Date hired_date;
	Date retired_date;
	String years_service; //근속연수
	boolean mid_term;   //중간정산
	boolean end_term;   //퇴직정산
	
	
	public RetireMemberRequest() { }  //기본 생성자
	
	
	 // 전체 생성자
	public RetireMemberRequest(String state, int emp_no, String name_kor, String dept, String job, Date hired_date,
			Date retired_date, String years_service, boolean mid_term, boolean end_term) {
		this.state = state;
		this.emp_no = emp_no;
		this.name_kor = name_kor;
		this.dept = dept;
		this.job = job;
		this.hired_date = hired_date;
		this.retired_date = retired_date;
		this.years_service = years_service;
		this.mid_term = mid_term;
		this.end_term = end_term;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public int getEmp_no() {
		return emp_no;
	}


	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}


	public String getName_kor() {
		return name_kor;
	}


	public void setName_kor(String name_kor) {
		this.name_kor = name_kor;
	}


	public String getDept() {
		return dept;
	}


	public void setDept(String dept) {
		this.dept = dept;
	}


	public String getJob() {
		return job;
	}


	public void setJob(String job) {
		this.job = job;
	}


	public Date getHired_date() {
		return hired_date;
	}


	public void setHired_date(Date hired_date) {
		this.hired_date = hired_date;
	}


	public Date getRetired_date() {
		return retired_date;
	}


	public void setRetired_date(Date retired_date) {
		this.retired_date = retired_date;
	}


	public String getYears_service() {
		return years_service;
	}


	public void setYears_service(String years_service) {
		this.years_service = years_service;
	}


	public boolean isMid_term() {
		return mid_term;
	}


	public void setMid_term(boolean mid_term) {
		this.mid_term = mid_term;
	}


	public boolean isEnd_term() {
		return end_term;
	}


	public void setEnd_term(boolean end_term) {
		this.end_term = end_term;
	}
	
}
