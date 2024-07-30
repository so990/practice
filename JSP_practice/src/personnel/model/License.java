package personnel.model;

import java.util.Date;

public class License {

	private int emp_no;
	private String lsc_name;
	private Date lsc_date;
	private String lsc_dep;
	private String lsc_num;
	private String lsc_note;
	private String lang_name;
	private String lang_test;
	private String lang_score;
	private Date lang_date;
	private String lang_read;
	private String lang_listen;
	private String lang_speak;
	
	public License(int emp_no, String lsc_name, Date lsc_date, String lsc_dep, String lsc_num, String lsc_note,
			String lang_name, String lang_test, String lang_score, Date lang_date, String lang_read, String lang_listen,
			String lang_speak) {
		
		this.emp_no = emp_no;
		this.lsc_name = lsc_name;
		this.lsc_date = lsc_date;
		this.lsc_dep = lsc_dep;
		this.lsc_num = lsc_num;
		this.lsc_note = lsc_note;
		this.lang_name = lang_name;
		this.lang_test = lang_test;
		this.lang_score = lang_score;
		this.lang_date = lang_date;
		this.lang_read = lang_read;
		this.lang_listen = lang_listen;
		this.lang_speak = lang_speak;
	}

	public int getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}

	public String getLsc_name() {
		return lsc_name;
	}

	public void setLsc_name(String lsc_name) {
		this.lsc_name = lsc_name;
	}

	public Date getLsc_date() {
		return lsc_date;
	}

	public void setLsc_date(Date lsc_date) {
		this.lsc_date = lsc_date;
	}

	public String getLsc_dep() {
		return lsc_dep;
	}

	public void setLsc_dep(String lsc_dep) {
		this.lsc_dep = lsc_dep;
	}

	public String getLsc_num() {
		return lsc_num;
	}

	public void setLsc_num(String lsc_num) {
		this.lsc_num = lsc_num;
	}

	public String getLsc_note() {
		return lsc_note;
	}

	public void setLsc_note(String lsc_note) {
		this.lsc_note = lsc_note;
	}

	public String getLang_name() {
		return lang_name;
	}

	public void setLang_name(String lang_name) {
		this.lang_name = lang_name;
	}

	public String getLang_test() {
		return lang_test;
	}

	public void setLang_test(String lang_test) {
		this.lang_test = lang_test;
	}

	public String getLang_score() {
		return lang_score;
	}

	public void setLang_score(String lang_score) {
		this.lang_score = lang_score;
	}

	public Date getLang_date() {
		return lang_date;
	}

	public void setLang_date(Date lang_date) {
		this.lang_date = lang_date;
	}

	public String getLang_read() {
		return lang_read;
	}

	public void setLang_read(String lang_read) {
		this.lang_read = lang_read;
	}

	public String getLang_listen() {
		return lang_listen;
	}

	public void setLang_listen(String lang_listen) {
		this.lang_listen = lang_listen;
	}

	public String getLang_speak() {
		return lang_speak;
	}

	public void setLang_speak(String lang_speak) {
		this.lang_speak = lang_speak;
	}
}
