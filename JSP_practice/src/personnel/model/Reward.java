package personnel.model;

import java.util.Date;

public class Reward {

	private int emp_no;
	private String reward_type;
	private String reward_name;
	private String reward_who;
	private Date reward_date;
	private String reward_detail;
	private String reward_note;
	
	public Reward(int emp_no, String reward_type, String reward_name, String reward_who, Date reward_date,
			String reward_detail, String reward_note) {
		
		this.emp_no = emp_no;
		this.reward_type = reward_type;
		this.reward_name = reward_name;
		this.reward_who = reward_who;
		this.reward_date = reward_date;
		this.reward_detail = reward_detail;
		this.reward_note = reward_note;
	}

	public int getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}

	public String getReward_type() {
		return reward_type;
	}

	public void setReward_type(String reward_type) {
		this.reward_type = reward_type;
	}

	public String getReward_name() {
		return reward_name;
	}

	public void setReward_name(String reward_name) {
		this.reward_name = reward_name;
	}

	public String getReward_who() {
		return reward_who;
	}

	public void setReward_who(String reward_who) {
		this.reward_who = reward_who;
	}

	public Date getReward_date() {
		return reward_date;
	}

	public void setReward_date(Date reward_date) {
		this.reward_date = reward_date;
	}

	public String getReward_detail() {
		return reward_detail;
	}

	public void setReward_detail(String reward_detail) {
		this.reward_detail = reward_detail;
	}

	public String getReward_note() {
		return reward_note;
	}

	public void setReward_note(String reward_note) {
		this.reward_note = reward_note;
	}
	
}
