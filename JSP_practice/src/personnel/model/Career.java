package personnel.model;

public class Career {
	
	private int emp_no;
	private String firm;
	private String firm_start;
	private String firm_end;
	private String firm_term;
	private String firm_job;
	private String firm_task;
	private String firm_retire;
	
	public Career(int emp_no, String firm, String firm_start, String firm_end, String firm_term, String firm_job,
			String firm_task, String firm_retire) {
		
		this.emp_no = emp_no;
		this.firm = firm;
		this.firm_start = firm_start;
		this.firm_end = firm_end;
		this.firm_term = firm_term;
		this.firm_job = firm_job;
		this.firm_task = firm_task;
		this.firm_retire = firm_retire;
	}

	public int getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}

	public String getFirm() {
		return firm;
	}

	public void setFirm(String firm) {
		this.firm = firm;
	}

	public String getFirm_start() {
		return firm_start;
	}

	public void setFirm_start(String firm_start) {
		this.firm_start = firm_start;
	}

	public String getFirm_end() {
		return firm_end;
	}

	public void setFirm_end(String firm_end) {
		this.firm_end = firm_end;
	}

	public String getFirm_term() {
		return firm_term;
	}

	public void setFirm_term(String firm_term) {
		this.firm_term = firm_term;
	}

	public String getFirm_job() {
		return firm_job;
	}

	public void setFirm_job(String firm_job) {
		this.firm_job = firm_job;
	}

	public String getFirm_task() {
		return firm_task;
	}

	public void setFirm_task(String firm_task) {
		this.firm_task = firm_task;
	}

	public String getFirm_retire() {
		return firm_retire;
	}

	public void setFirm_retire(String firm_retire) {
		this.firm_retire = firm_retire;
	}

}
