package payded_items.service;

import java.util.Map;

public class Deduction_itemsRequest {

	private String ded_name;
	private String ded_memo;
	private String ded_cut_unit;
	private String ded_note;
	private String ded_used;

	public Deduction_itemsRequest(String ded_name, String ded_memo, String ded_cut_unit, String ded_note,
			String ded_used) {
	
		this.ded_name = ded_name;
		this.ded_memo = ded_memo;
		this.ded_cut_unit = ded_cut_unit;
		this.ded_note = ded_note;
		this.ded_used = ded_used;
	}

	public String getDed_name() {
		return ded_name;
	}

	public void setDed_name(String ded_name) {
		this.ded_name = ded_name;
	}

	public String getDed_memo() {
		return ded_memo;
	}

	public void setDed_memo(String ded_memo) {
		this.ded_memo = ded_memo;
	}

	public String getDed_cut_unit() {
		return ded_cut_unit;
	}

	public void setDed_cut_unit(String ded_cut_unit) {
		this.ded_cut_unit = ded_cut_unit;
	}

	public String getDed_note() {
		return ded_note;
	}

	public void setDed_note(String ded_note) {
		this.ded_note = ded_note;
	}

	public String getDed_used() {
		return ded_used;
	}

	public void setDed_used(String ded_used) {
		this.ded_used = ded_used;
	}

	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors, ded_name, "ded_name");
	}

	private void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
		if(value==null||value.isEmpty())
			errors.put(fieldName, Boolean.TRUE);
	}
}