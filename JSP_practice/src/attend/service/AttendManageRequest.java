package attend.service;

import java.sql.Date;
import java.util.Map;

public class AttendManageRequest {
	private int emp_no;
	private Date atth_inserted;
	private String atth_name;
	private Date atth_start;
	private Date atth_end;
	private int atth_days;
	private String atth_cost;
	private String atth_note;

	
	public AttendManageRequest(int emp_no, Date atth_inserted, String atth_name, Date atth_start, Date atth_end,
			int atth_days, String atth_cost, String atth_note) {
		super();
		this.emp_no = emp_no;
		this.atth_inserted = atth_inserted;
		this.atth_name = atth_name;
		this.atth_start = atth_start;
		this.atth_end = atth_end;
		this.atth_days = atth_days;
		this.atth_cost = atth_cost;
		this.atth_note = atth_note;
	}

	
	public int getEmp_no() {
		return emp_no;
	}


	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}


	public Date getAtth_inserted() {
		return atth_inserted;
	}


	public void setAtth_inserted(Date atth_inserted) {
		this.atth_inserted = atth_inserted;
	}


	public String getAtth_name() {
		return atth_name;
	}


	public void setAtth_name(String atth_name) {
		this.atth_name = atth_name;
	}


	public Date getAtth_start() {
		return atth_start;
	}


	public void setAtth_start(Date atth_start) {
		this.atth_start = atth_start;
	}


	public Date getAtth_end() {
		return atth_end;
	}


	public void setAtth_end(Date atth_end) {
		this.atth_end = atth_end;
	}


	public int getAtth_days() {
		return atth_days;
	}


	public void setAtth_days(int atth_days) {
		this.atth_days = atth_days;
	}


	public String getAtth_cost() {
		return atth_cost;
	}


	public void setAtth_cost(String atth_cost) {
		this.atth_cost = atth_cost;
	}


	public String getAtth_note() {
		return atth_note;
	}


	public void setAtth_note(String atth_note) {
		this.atth_note = atth_note;
	}


	public void validate(Map<String, Boolean> errors) {
		// 아이디,이름등의 정보가 입력되었는지, password 와 confirmPassword가 일치하지 확인하여
		// 문제가 있을시 errors 맵에 키<String>과 함께 True를 저장하는 매서드
	
		checkEmpty(errors, atth_inserted, "atth_inserted");
		checkEmpty(errors, atth_name, "atth_name");
		checkEmpty(errors, atth_start, "atth_start");
		checkEmpty(errors, atth_end, "atth_end");
	
		checkEmpty(errors, atth_cost, "atth_cost");
		checkEmpty(errors, atth_note, "atth_note");
	}
	
	private void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
		if(value == null || value.isEmpty())
			errors.put(fieldName, Boolean.TRUE);
	}
	private void checkEmpty(Map<String, Boolean> errors, Date value, String fieldName) {
		if(value == null || value.toString().isEmpty())
			errors.put(fieldName, Boolean.TRUE);
	}

}