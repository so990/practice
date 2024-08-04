package payded_items.service;

import java.util.Date;
import java.util.Map;

public class Payment_itemsRequest {

	private String pay_name;
	private String pay_tax;
	private String tax_free_name;
	private String tax_free_limit;
	private String tax_memo;
	private String cut_unit;
	private String attend_conn;
	private String pay_cost;
	private String pay_used;
	
	public Payment_itemsRequest(String pay_name, String pay_tax, String tax_free_name, String tax_free_limit,
			String tax_memo, String cut_unit, String attend_conn, String pay_cost, String pay_used) {
	
		this.pay_name = pay_name;
		this.pay_tax = pay_tax;
		this.tax_free_name = tax_free_name;
		this.tax_free_limit = tax_free_limit;
		this.tax_memo = tax_memo;
		this.cut_unit = cut_unit;
		this.attend_conn = attend_conn;
		this.pay_cost = pay_cost;
		this.pay_used = pay_used;
	}

	public String getPay_name() {
		return pay_name;
	}

	public void setPay_name(String pay_name) {
		this.pay_name = pay_name;
	}

	public String getPay_tax() {
		return pay_tax;
	}

	public void setPay_tax(String pay_tax) {
		this.pay_tax = pay_tax;
	}

	public String getTax_free_name() {
		return tax_free_name;
	}

	public void setTax_free_name(String tax_free_name) {
		this.tax_free_name = tax_free_name;
	}

	public String getTax_free_limit() {
		return tax_free_limit;
	}

	public void setTax_free_limit(String tax_free_limit) {
		this.tax_free_limit = tax_free_limit;
	}

	public String getTax_memo() {
		return tax_memo;
	}

	public void setTax_memo(String tax_memo) {
		this.tax_memo = tax_memo;
	}

	public String getCut_unit() {
		return cut_unit;
	}

	public void setCut_unit(String cut_unit) {
		this.cut_unit = cut_unit;
	}

	public String getAttend_conn() {
		return attend_conn;
	}

	public void setAttend_conn(String attend_conn) {
		this.attend_conn = attend_conn;
	}

	public String getPay_cost() {
		return pay_cost;
	}

	public void setPay_cost(String pay_cost) {
		this.pay_cost = pay_cost;
	}

	public String getPay_used() {
		return pay_used;
	}

	public void setPay_used(String pay_used) {
		this.pay_used = pay_used;
	}

	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors, pay_name, "pay_name");
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