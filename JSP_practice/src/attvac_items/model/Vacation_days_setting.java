package attvac_items.model;

import java.sql.Date;

public class Vacation_days_setting {
		
		private String emp_type;
		private int emp_no;
		private String name_kor;
		private String dept;
		private String job;
		private Date hired_date;
		private int has_vac_days;
		
		public Vacation_days_setting(String emp_type, int emp_no, String name_kor, String dept, String job,
				Date hired_date, int has_vac_days) {
			
			this.emp_type = emp_type;
			this.emp_no = emp_no;
			this.name_kor = name_kor;
			this.dept = dept;
			this.job = job;
			this.hired_date = hired_date;
			this.has_vac_days = has_vac_days;
		}

		public String getEmp_type() {
			return emp_type;
		}

		public void setEmp_type(String emp_type) {
			this.emp_type = emp_type;
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

		public Date getHired_date() {
			return hired_date;
		}

		public void setHired_date(Date hired_date) {
			this.hired_date = hired_date;
		}

		public int getHas_vac_days() {
			return has_vac_days;
		}

		public void setHas_vac_days(int has_vac_days) {
			this.has_vac_days = has_vac_days;
		}

		
}
