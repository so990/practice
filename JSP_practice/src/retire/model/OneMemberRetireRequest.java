package retire.model;

import java.util.Date;

public class OneMemberRetireRequest {

		private int emp_no;
		private String retire_type; //퇴직구분(정년퇴직...)
		private Date retire_date; // 퇴직일자
	    private String retire_reason; //퇴직사유
	    private String retire_phone; //퇴직 후 연락처
	    
	    
		public OneMemberRetireRequest() {}
		
		public OneMemberRetireRequest(int emp_no, String retire_type, Date retire_date, String retire_reason,
				String retire_phone) {
			this.emp_no = emp_no;
			this.retire_type = retire_type;
			this.retire_date = retire_date;
			this.retire_reason = retire_reason;
			this.retire_phone = retire_phone;
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

		public Date getRetire_date() {
			return retire_date;
		}

		public void setRetire_date(Date retire_date) {
			this.retire_date = retire_date;
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

		
		@Override
		public String toString() {
			return "OneMemberRetireRequest [emp_no=" + emp_no + ", retire_type=" + retire_type + ", retire_date="
					+ retire_date + ", retire_reason=" + retire_reason + ", retire_phone=" + retire_phone + "]";
		}
		
		
	    
	    
	    
	    
}
