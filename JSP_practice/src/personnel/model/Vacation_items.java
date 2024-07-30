package personnel.model;

import java.util.Date;

public class Vacation_items {

	private String vac_name;
	private Date vac_start;
	private Date vac_end;
	private String vac_used;
	
	public Vacation_items(String vac_name, Date vac_start, Date vac_end, String vac_used) {
	
		this.vac_name = vac_name;
		this.vac_start = vac_start;
		this.vac_end = vac_end;
		this.vac_used = vac_used;
	}

	public String getVac_name() {
		return vac_name;
	}

	public void setVac_name(String vac_name) {
		this.vac_name = vac_name;
	}

	public Date getVac_start() {
		return vac_start;
	}

	public void setVac_start(Date vac_start) {
		this.vac_start = vac_start;
	}

	public Date getVac_end() {
		return vac_end;
	}

	public void setVac_end(Date vac_end) {
		this.vac_end = vac_end;
	}

	public String getVac_used() {
		return vac_used;
	}

	public void setVac_used(String vac_used) {
		this.vac_used = vac_used;
	}
}
