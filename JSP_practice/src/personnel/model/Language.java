package personnel.model;

public class Language {
	private int emp_no;
	private String lang_name;
	private String lang_test;
	private String lang_score;
	private String lang_date;
	private String lang_read;
	private String lang_listen;
	private String lang_speak;
	
	public Language(int emp_no, String lang_name, String lang_test, String lang_score, String lang_date, String lang_read,
			String lang_listen, String lang_speak) {
		super();
		this.emp_no = emp_no;
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

	public String getLang_date() {
		return lang_date;
	}

	public void setLang_date(String lang_date) {
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
