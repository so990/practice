package personnel.service;

import java.sql.Date;

public class RetireRequest {
	private int emp_no;
	private String retire_type;
	private Date retired_date;
	private String retire_reason;
	private String retire_phone;
	private String retire_cost;
	
	public RetireRequest(int emp_no, String retire_type, Date retired_date, String retire_reason, String retire_phone,
			String retire_cost) {
		super();
		this.emp_no = emp_no;
		this.retire_type = retire_type;
		this.retired_date = retired_date;
		this.retire_reason = retire_reason;
		this.retire_phone = retire_phone;
		this.retire_cost = retire_cost;
	}
	public int getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}
	public String getRetire_type() {
		return retire_type;
	}
	public void setRetire_type(String retire_type) {
		this.retire_type = retire_type;
	}
	public Date getRetired_date() {
		return retired_date;
	}
	public void setRetired_date(Date retired_date) {
		this.retired_date = retired_date;
	}
	public String getRetire_reason() {
		return retire_reason;
	}
	public void setRetire_reason(String retire_reason) {
		this.retire_reason = retire_reason;
	}
	public String getRetire_phone() {
		return retire_phone;
	}
	public void setRetire_phone(String retire_phone) {
		this.retire_phone = retire_phone;
	}
	public String getRetire_cost() {
		return retire_cost;
	}
	public void setRetire_cost(String retire_cost) {
		this.retire_cost = retire_cost;
	}
	
	
}
