package personnel.service;

public class MilitaryRequest {
	private int emp_no;
	private String mil;
	private String mil_type;
	private String mil_start;
	private String mil_end;
	private String mil_rank;
	private String mil_job;
	private String mil_no_reason;
	
	public MilitaryRequest(int emp_no, String mil, String mil_type, String mil_start, String mil_end, String mil_rank,
			String mil_job, String mil_no_reason) {
		super();
		this.emp_no = emp_no;
		this.mil = mil;
		this.mil_type = mil_type;
		this.mil_start = mil_start;
		this.mil_end = mil_end;
		this.mil_rank = mil_rank;
		this.mil_job = mil_job;
		this.mil_no_reason = mil_no_reason;
	}
	public int getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}
	public String getMil() {
		return mil;
	}
	public void setMil(String mil) {
		this.mil = mil;
	}
	public String getMil_type() {
		return mil_type;
	}
	public void setMil_type(String mil_type) {
		this.mil_type = mil_type;
	}
	public String getMil_start() {
		return mil_start;
	}
	public void setMil_start(String mil_start) {
		this.mil_start = mil_start;
	}
	public String getMil_end() {
		return mil_end;
	}
	public void setMil_end(String mil_end) {
		this.mil_end = mil_end;
	}
	public String getMil_rank() {
		return mil_rank;
	}
	public void setMil_rank(String mil_rank) {
		this.mil_rank = mil_rank;
	}
	public String getMil_job() {
		return mil_job;
	}
	public void setMil_job(String mil_job) {
		this.mil_job = mil_job;
	}
	public String getMil_no_reason() {
		return mil_no_reason;
	}
	public void setMil_no_reason(String mil_no_reason) {
		this.mil_no_reason = mil_no_reason;
	}
	
}
