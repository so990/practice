package retire.model;

import java.sql.Date;

public class RetireProcessRequest {

	//사원 테이블
	private String state;
	private int  emp_no;
	private String name_kor;
	private String dept;
	private String job;
	private Date hired_date;
	private Date retired_date;
	private String years_service; //근속연수
	
	//퇴직 테이블
	private String retire_type; //퇴직구분(정년퇴직...)
	private Date retire_date; // 퇴직일자
    private String retire_reason; //퇴직사유
    private String retire_phone; //퇴직 후 연락처
    
    //퇴직급여 테이블
    private String ret_calc_type_mid;   //퇴직급여지급구분(중간정산)
    private String ret_calc_type_retire;   //퇴직급여지급구분(퇴직정산)
    
	public RetireProcessRequest() {}



	public RetireProcessRequest(String state, int emp_no, String name_kor, String dept, String job, Date hired_date,
			Date retired_date, String years_service, String retire_type, Date retire_date, String retire_reason,
			String retire_phone, String ret_calc_type_mid, String ret_calc_type_retire) {
		super();
		this.state = state;
		this.emp_no = emp_no;
		this.name_kor = name_kor;
		this.dept = dept;
		this.job = job;
		this.hired_date = hired_date;
		this.retired_date = retired_date;
		this.years_service = years_service;
		this.retire_type = retire_type;
		this.retire_date = retire_date;
		this.retire_reason = retire_reason;
		this.retire_phone = retire_phone;
		this.ret_calc_type_mid = ret_calc_type_mid;
		this.ret_calc_type_retire = ret_calc_type_retire;
	}





	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
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


	public Date getRetired_date() {
		return retired_date;
	}


	public void setRetired_date(Date retired_date) {
		this.retired_date = retired_date;
	}


	public String getYears_service() {
		return years_service;
	}


	public void setYears_service(String years_service) {
		this.years_service = years_service;
	}


	public String getRet_calc_type_mid() {
		return ret_calc_type_mid;
	}


	public void setRet_calc_type_mid(String ret_calc_type_mid) {
		this.ret_calc_type_mid = ret_calc_type_mid;
	}


	public String getRet_calc_type_retire() {
		return ret_calc_type_retire;
	}


	public void setRet_calc_type_retire(String ret_calc_type_retire) {
		this.ret_calc_type_retire = ret_calc_type_retire;
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
		return "RetireProcessRequest [state=" + state + ", emp_no=" + emp_no + ", name_kor=" + name_kor + ", dept="
				+ dept + ", job=" + job + ", hired_date=" + hired_date + ", retired_date=" + retired_date
				+ ", years_service=" + years_service + ", retire_type=" + retire_type + ", retire_date=" + retire_date
				+ ", retire_reason=" + retire_reason + ", retire_phone=" + retire_phone + ", ret_calc_type_mid="
				+ ret_calc_type_mid + ", ret_calc_type_retire=" + ret_calc_type_retire + "]";
	}


	

	
    
    
}
