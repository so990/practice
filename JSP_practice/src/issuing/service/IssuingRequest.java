package issuing.service;

import java.sql.Date;

public class IssuingRequest {
	
	private int emp_no;
	private int isu_num;
	private String isu_led;
	private String isu_pur;
	private Date isu_date;
	
	public IssuingRequest(int emp_no, int isu_num, String isu_led, String isu_pur, Date isu_date) {
		super();
		this.emp_no = emp_no;
		this.isu_num = isu_num;
		this.isu_led = isu_led;
		this.isu_pur = isu_pur;
		this.isu_date = isu_date;
	}

	public int getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}

	public int getIsu_num() {
		return isu_num;
	}

	public void setIsu_num(int isu_num) {
		this.isu_num = isu_num;
	}

	public String getIsu_led() {
		return isu_led;
	}

	public void setIsu_led(String isu_led) {
		this.isu_led = isu_led;
	}

	public String getIsu_pur() {
		return isu_pur;
	}

	public void setIsu_pur(String isu_pur) {
		this.isu_pur = isu_pur;
	}

	public Date getIsu_date() {
		return isu_date;
	}

	public void setIsu_date(Date isu_date) {
		this.isu_date = isu_date;
	}
	
	
}
