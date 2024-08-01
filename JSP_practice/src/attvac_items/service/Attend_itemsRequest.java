package attvac_items.service;

import java.util.Date;
import java.util.Map;

public class Attend_itemsRequest {

	private String att_name;
	private String att_unit;
	private String att_grp;
	private String att_deduction;
	private String att_conn;
	private String att_used;
	
	public Attend_itemsRequest(String att_name, String att_unit, String att_grp, String att_deduction, String att_conn,
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

	public String getAtt_unit() {
		return att_unit;
	}

	public void setAtt_unit(String att_unit) {
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

	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors, att_name, "att_name");
		checkEmpty(errors, att_unit, "att_unit");
		checkEmpty(errors, att_grp, "att_grp");
	}

	private void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
		if(value==null||value.isEmpty())
			errors.put(fieldName, Boolean.TRUE);
	}

	private void checkEmpty(Map<String, Boolean> errors, Date value, String fieldName) {
		if(value==null||value.toString().isEmpty())
			errors.put(fieldName, Boolean.TRUE);
	}
}