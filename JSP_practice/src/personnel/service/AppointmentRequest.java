package personnel.service;

import java.sql.Date;

public class AppointmentRequest {
	private int emp_no;
	private String appo_type;
	private Date appo_date;
	private String appo_dep;
	private String appo_job;
	private String appo_task;
	private String appo_note;
	
	public AppointmentRequest(int emp_no, String appo_type, Date appo_date, String appo_dep, String appo_job,
			String appo_task, String appo_note) {
		super();
		this.emp_no = emp_no;
		this.appo_type = appo_type;
		this.appo_date = appo_date;
		this.appo_dep = appo_dep;
		this.appo_job = appo_job;
		this.appo_task = appo_task;
		this.appo_note = appo_note;
		
	}
	
	public int getEmp_no() {
		return emp_no;
	}
	public String getAppo_type() {
		return appo_type;
	}
	public Date getAppo_date() {
		return appo_date;
	}
	public String getAppo_dep() {
		return appo_dep;
	}
	public String getAppo_job() {
		return appo_job;
	}
	public String getAppo_task() {
		return appo_task;
	}
	public String getAppo_note() {
		return appo_note;
	}
	
	
	
}
