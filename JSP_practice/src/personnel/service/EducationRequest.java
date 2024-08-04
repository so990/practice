package personnel.service;

public class EducationRequest {
	
	private int emp_no;
	private String school;
	private String school_start;
	private String school_end;
	private String school_name;
	private String school_major;
	private String school_state;
	
	
	public EducationRequest(int emp_no, String school, String school_start, String school_end, String school_name,
			String school_major, String school_state) {
		super();
		this.emp_no = emp_no;
		this.school = school;
		this.school_start = school_start;
		this.school_end = school_end;
		this.school_name = school_name;
		this.school_major = school_major;
		this.school_state = school_state;
	}
	
	public int getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getSchool_start() {
		return school_start;
	}
	public void setSchool_start(String school_start) {
		this.school_start = school_start;
	}
	public String getSchool_end() {
		return school_end;
	}
	public void setSchool_end(String school_end) {
		this.school_end = school_end;
	}
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	public String getSchool_major() {
		return school_major;
	}
	public void setSchool_major(String school_major) {
		this.school_major = school_major;
	}
	public String getSchool_state() {
		return school_state;
	}
	public void setSchool_state(String school_state) {
		this.school_state = school_state;
	}

}
