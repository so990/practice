package personnel.model;

public class License {

	private int emp_no;
	private String lsc_name;
	private String lsc_date;
	private String lsc_dep;
	private String lsc_num;
	private String lsc_note;
	
	public License(int emp_no, String lsc_name, String lsc_date, String lsc_dep, String lsc_num, String lsc_note) {
		super();
		this.emp_no = emp_no;
		this.lsc_name = lsc_name;
		this.lsc_date = lsc_date;
		this.lsc_dep = lsc_dep;
		this.lsc_num = lsc_num;
		this.lsc_note = lsc_note;
	}

	public int getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}

	public String getLsc_name() {
		return lsc_name;
	}

	public void setLsc_name(String lsc_name) {
		this.lsc_name = lsc_name;
	}

	public String getLsc_date() {
		return lsc_date;
	}

	public void setLsc_date(String lsc_date) {
		this.lsc_date = lsc_date;
	}

	public String getLsc_dep() {
		return lsc_dep;
	}

	public void setLsc_dep(String lsc_dep) {
		this.lsc_dep = lsc_dep;
	}

	public String getLsc_num() {
		return lsc_num;
	}

	public void setLsc_num(String lsc_num) {
		this.lsc_num = lsc_num;
	}

	public String getLsc_note() {
		return lsc_note;
	}

	public void setLsc_note(String lsc_note) {
		this.lsc_note = lsc_note;
	}
	
	
}
