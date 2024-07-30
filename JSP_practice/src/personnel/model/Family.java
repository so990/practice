package personnel.model;

public class Family {
	
	private int emp_no;
	private String f_relation;
	private String f_name;
	private String f_nationality;
	private String f_id_num;
	private String f_disability;
	private String f_per_deduction;
	private String f_heal_insur;
	private String f_live;
	private String f_gapgeunse;
	private String f_child;
	
	public Family(int emp_no, String f_relation, String f_name, String f_nationality, String f_id_num,
			String f_disability, String f_per_deduction, String f_heal_insur, String f_live, String f_gapgeunse,
			String f_child) {
	
		this.emp_no = emp_no;
		this.f_relation = f_relation;
		this.f_name = f_name;
		this.f_nationality = f_nationality;
		this.f_id_num = f_id_num;
		this.f_disability = f_disability;
		this.f_per_deduction = f_per_deduction;
		this.f_heal_insur = f_heal_insur;
		this.f_live = f_live;
		this.f_gapgeunse = f_gapgeunse;
		this.f_child = f_child;
	}

	public int getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}

	public String getF_relation() {
		return f_relation;
	}

	public void setF_relation(String f_relation) {
		this.f_relation = f_relation;
	}

	public String getF_name() {
		return f_name;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public String getF_nationality() {
		return f_nationality;
	}

	public void setF_nationality(String f_nationality) {
		this.f_nationality = f_nationality;
	}

	public String getF_id_num() {
		return f_id_num;
	}

	public void setF_id_num(String f_id_num) {
		this.f_id_num = f_id_num;
	}

	public String getF_disability() {
		return f_disability;
	}

	public void setF_disability(String f_disability) {
		this.f_disability = f_disability;
	}

	public String getF_per_deduction() {
		return f_per_deduction;
	}

	public void setF_per_deduction(String f_per_deduction) {
		this.f_per_deduction = f_per_deduction;
	}

	public String getF_heal_insur() {
		return f_heal_insur;
	}

	public void setF_heal_insur(String f_heal_insur) {
		this.f_heal_insur = f_heal_insur;
	}

	public String getF_live() {
		return f_live;
	}

	public void setF_live(String f_live) {
		this.f_live = f_live;
	}

	public String getF_gapgeunse() {
		return f_gapgeunse;
	}

	public void setF_gapgeunse(String f_gapgeunse) {
		this.f_gapgeunse = f_gapgeunse;
	}

	public String getF_child() {
		return f_child;
	}

	public void setF_child(String f_child) {
		this.f_child = f_child;
	}
	

}
