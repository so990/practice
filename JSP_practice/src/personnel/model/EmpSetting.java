package personnel.model;

import java.sql.Date;

public class EmpSetting {

	private String emp_type;
	private Date hired_date;
	private int emp_no;
	private String name_kor;
	private String dept;
	private String job;
	private String id_number;
	private String phone;
	private String email;
	private Date retired_date;
	private String state;
	
	public EmpSetting(String emp_type, Date hired_date, int emp_no, String name_kor, String dept, String job,
			String id_number, String phone, String email, Date retired_date, String state) {
		super();
		this.emp_type = emp_type;
		this.hired_date = hired_date;
		this.emp_no = emp_no;
		this.name_kor = name_kor;
		this.dept = dept;
		this.job = job;
		this.id_number = id_number;
		this.phone = phone;
		this.email = email;
		this.retired_date = retired_date;
		this.state = state;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getEmp_type() {
		return emp_type;
	}

	public void setEmp_type(String emp_type) {
		this.emp_type = emp_type;
	}

	public Date getHired_date() {
		return hired_date;
	}

	public void setHired_date(Date hired_date) {
		this.hired_date = hired_date;
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

	public String getId_number() {
		return id_number;
	}

	public void setId_number(String id_number) {
		this.id_number = id_number;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRetired_date() {
		return retired_date;
	}

	public void setRetired_date(Date retired_date) {
		this.retired_date = retired_date;
	}
	
	
}
