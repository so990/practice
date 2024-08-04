package personnel.model;

import java.util.List;

public class Personnel {
	
	Employee emp;
	Insurance ins;
	List<Education> edu;
	List<Career> car;
	Military mil;
	List<License> lcs;
	List<Language> lang;
	Retire rtr;
	
	public Personnel(Employee emp, Insurance ins, List<Education> edu, List<Career> car, Military mil,
			List<License> lcs, List<Language> lang, Retire rtr) {
		super();
		this.emp = emp;
		this.ins = ins;
		this.edu = edu;
		this.car = car;
		this.mil = mil;
		this.lcs = lcs;
		this.lang = lang;
		this.rtr = rtr;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public Insurance getIns() {
		return ins;
	}

	public void setIns(Insurance ins) {
		this.ins = ins;
	}

	public List<Education> getEdu() {
		return edu;
	}

	public void setEdu(List<Education> edu) {
		this.edu = edu;
	}

	public List<Career> getCar() {
		return car;
	}

	public void setCar(List<Career> car) {
		this.car = car;
	}

	public Military getMil() {
		return mil;
	}

	public void setMil(Military mil) {
		this.mil = mil;
	}

	public List<License> getLcs() {
		return lcs;
	}

	public void setLcs(List<License> lcs) {
		this.lcs = lcs;
	}

	public List<Language> getLang() {
		return lang;
	}

	public void setLang(List<Language> lang) {
		this.lang = lang;
	}

	public Retire getRtr() {
		return rtr;
	}

	public void setRtr(Retire rtr) {
		this.rtr = rtr;
	}
	
}