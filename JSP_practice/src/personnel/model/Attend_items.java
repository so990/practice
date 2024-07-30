package personnel.model;

import java.util.Date;

public class Attend_items {
	
	private String att_name;
	private Date att_unit;
	private String att_grp;
	private String att_deduction;
	private String att_conn;
	private String att_used;
	
	public Attend_items(String att_name, Date att_unit, String att_grp, String att_deduction, String att_conn,
			String att_used) {
		
		this.att_name = att_name;
		this.att_unit = att_unit;
		this.att_grp = att_grp;
		this.att_deduction = att_deduction;
		this.att_conn = att_conn;
		this.att_used = att_used;
	}

	public String getAtt_name() {
		return att_name;
	}

	public void setAtt_name(String att_name) {
		this.att_name = att_name;
	}

	public Date getAtt_unit() {
		return att_unit;
	}

	public void setAtt_unit(Date att_unit) {
		this.att_unit = att_unit;
	}

	public String getAtt_grp() {
		return att_grp;
	}

	public void setAtt_grp(String att_grp) {
		this.att_grp = att_grp;
	}

	public String getAtt_deduction() {
		return att_deduction;
	}

	public void setAtt_deduction(String att_deduction) {
		this.att_deduction = att_deduction;
	}

	public String getAtt_conn() {
		return att_conn;
	}

	public void setAtt_conn(String att_conn) {
		this.att_conn = att_conn;
	}

	public String getAtt_used() {
		return att_used;
	}

	public void setAtt_used(String att_used) {
		this.att_used = att_used;
	}

}
