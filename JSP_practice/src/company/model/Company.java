package company.model;

import java.sql.Date;

public class Company {
	private String cp_name;
	private String ceo_job;
	private String ceo_name;
	private String bs_num;
	private String bs_regnum;
	private Date founded_date;
	private String hp;
	private String bs_post;
	private String bs_addr;
	private String bs_phone;
	private String bs_fax;
	private String bs_type;
	private String cp_type;
	private Date calc_start;
	private Date calc_end;
	private Date payday;
	private String bs_bank;
	private String bs_account;
	private String bs_acc_name;
	
	public Company(String cp_name, String ceo_job, String ceo_name, String bs_num, String bs_regnum, Date founded_date,
			String hp, String bs_post, String bs_addr, String bs_phone, String bs_fax, String bs_type, String cp_type,
			Date calc_start, Date calc_end, Date payday, String bs_bank, String bs_account, String bs_acc_name) {
		super();
		this.cp_name = cp_name;
		this.ceo_job = ceo_job;
		this.ceo_name = ceo_name;
		this.bs_num = bs_num;
		this.bs_regnum = bs_regnum;
		this.founded_date = founded_date;
		this.hp = hp;
		this.bs_post = bs_post;
		this.bs_addr = bs_addr;
		this.bs_phone = bs_phone;
		this.bs_fax = bs_fax;
		this.bs_type = bs_type;
		this.cp_type = cp_type;
		this.calc_start = calc_start;
		this.calc_end = calc_end;
		this.payday = payday;
		this.bs_bank = bs_bank;
		this.bs_account = bs_account;
		this.bs_acc_name = bs_acc_name;
		
	}

	public String getCp_name() {
		return cp_name;
	}

	public String getCeo_job() {
		return ceo_job;
	}

	public String getCeo_name() {
		return ceo_name;
	}

	public String getBs_num() {
		return bs_num;
	}

	public String getBs_regnum() {
		return bs_regnum;
	}

	public Date getFounded_date() {
		return founded_date;
	}

	public String getHp() {
		return hp;
	}

	public String getBs_post() {
		return bs_post;
	}

	public String getBs_addr() {
		return bs_addr;
	}

	public String getBs_phone() {
		return bs_phone;
	}

	public String getBs_fax() {
		return bs_fax;
	}

	public String getBs_type() {
		return bs_type;
	}

	public String getCp_type() {
		return cp_type;
	}

	public Date getCalc_start() {
		return calc_start;
	}

	public Date getCalc_end() {
		return calc_end;
	}

	public Date getPayday() {
		return payday;
	}

	public String getBs_bank() {
		return bs_bank;
	}

	public String getBs_account() {
		return bs_account;
	}

	public String getBs_acc_name() {
		return bs_acc_name;
	}

	public void setCp_name(String cp_name) {
		this.cp_name = cp_name;
	}

	public void setCeo_job(String ceo_job) {
		this.ceo_job = ceo_job;
	}

	public void setCeo_name(String ceo_name) {
		this.ceo_name = ceo_name;
	}

	public void setBs_num(String bs_num) {
		this.bs_num = bs_num;
	}

	public void setBs_regnum(String bs_regnum) {
		this.bs_regnum = bs_regnum;
	}

	public void setFounded_date(Date founded_date) {
		this.founded_date = founded_date;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public void setBs_post(String bs_post) {
		this.bs_post = bs_post;
	}

	public void setBs_addr(String bs_addr) {
		this.bs_addr = bs_addr;
	}

	public void setBs_phone(String bs_phone) {
		this.bs_phone = bs_phone;
	}

	public void setBs_fax(String bs_fax) {
		this.bs_fax = bs_fax;
	}

	public void setBs_type(String bs_type) {
		this.bs_type = bs_type;
	}

	public void setCp_type(String cp_type) {
		this.cp_type = cp_type;
	}

	public void setCalc_start(Date calc_start) {
		this.calc_start = calc_start;
	}

	public void setCalc_end(Date calc_end) {
		this.calc_end = calc_end;
	}

	public void setPayday(Date payday) {
		this.payday = payday;
	}

	public void setBs_bank(String bs_bank) {
		this.bs_bank = bs_bank;
	}

	public void setBs_account(String bs_account) {
		this.bs_account = bs_account;
	}

	public void setBs_acc_name(String bs_acc_name) {
		this.bs_acc_name = bs_acc_name;
	}
	
}
