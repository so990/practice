package personnel.service;

public class StudyRequest {
	private int emp_no;
	private String study_type;
	private String study_name;
	private String study_start;
	private String study_end;
	private String study_dep;
	private String study_cost;
	private String study_refund;
	
	public StudyRequest(int emp_no, String study_type, String study_name, String study_start, String study_end,
			String study_dep, String study_cost, String study_refund) {
		super();
		this.emp_no = emp_no;
		this.study_type = study_type;
		this.study_name = study_name;
		this.study_start = study_start;
		this.study_end = study_end;
		this.study_dep = study_dep;
		this.study_cost = study_cost;
		this.study_refund = study_refund;
	}

	public int getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}

	public String getStudy_type() {
		return study_type;
	}

	public void setStudy_type(String study_type) {
		this.study_type = study_type;
	}

	public String getStudy_name() {
		return study_name;
	}

	public void setStudy_name(String study_name) {
		this.study_name = study_name;
	}

	public String getStudy_start() {
		return study_start;
	}

	public void setStudy_start(String study_start) {
		this.study_start = study_start;
	}

	public String getStudy_end() {
		return study_end;
	}

	public void setStudy_end(String study_end) {
		this.study_end = study_end;
	}

	public String getStudy_dep() {
		return study_dep;
	}

	public void setStudy_dep(String study_dep) {
		this.study_dep = study_dep;
	}

	public String getStudy_cost() {
		return study_cost;
	}

	public void setStudy_cost(String study_cost) {
		this.study_cost = study_cost;
	}

	public String getStudy_refund() {
		return study_refund;
	}

	public void setStudy_refund(String study_refund) {
		this.study_refund = study_refund;
	}
	
	
	
}
