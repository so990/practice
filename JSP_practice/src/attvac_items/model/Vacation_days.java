package attvac_items.model;

public class Vacation_days {
	
	private int emp_no;
	private String vac_name;
	private int has_vac_days;
	
	public Vacation_days(int emp_no, String vac_name, int has_vac_days) {
		
		this.emp_no = emp_no;
		this.vac_name = vac_name;
		this.has_vac_days = has_vac_days;
	}

	public int getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}

	public String getVac_name() {
		return vac_name;
	}

	public void setVac_name(String vac_name) {
		this.vac_name = vac_name;
	}

	public int getHas_vac_days() {
		return has_vac_days;
	}

	public void setHas_vac_days(int has_vac_days) {
		this.has_vac_days = has_vac_days;
	}
}
