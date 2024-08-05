package personnel.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import personnel.model.Career;
import personnel.model.Education;
import personnel.model.Employee;
import personnel.model.Insurance;
import personnel.model.Language;
import personnel.model.License;
import personnel.model.Military;
import personnel.model.Personnel;
import personnel.model.Retire;
import personnel.service.SelectCareerService;
import personnel.service.SelectEducationService;
import personnel.service.SelectEmployeeService;
import personnel.service.SelectInsuranceService;
import personnel.service.SelectLanguageService;
import personnel.service.SelectLicenseService;
import personnel.service.SelectMilitaryService;
import personnel.service.SelectRetireService;

public class PersonnelCardHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/personnel/personnelCard.jsp";
	
	private SelectCareerService selectCareerService = new SelectCareerService();
	private SelectEducationService selectEducationService = new SelectEducationService();
	private SelectEmployeeService selectEmployeeService = new SelectEmployeeService();
	private SelectInsuranceService selectInsuranceService = new SelectInsuranceService();
	private SelectLanguageService selectLanguageService = new SelectLanguageService();
	private SelectLicenseService selectLicenseService = new SelectLicenseService();
	private SelectMilitaryService selectMilitaryService = new SelectMilitaryService();
	private SelectRetireService selectRetireService = new SelectRetireService();

	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("get")) { // get 요청이 오면 FORM_VIEW
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("post")) {// post 요청이 오면 processSubmit()로 이동
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		
		List<Employee> emp_list = selectEmployeeService.selectAll();
		
		req.setAttribute("emp_list", emp_list);

		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		try {
			List<Employee> emp_list = selectEmployeeService.selectAll();
			req.setAttribute("emp_list", emp_list);

			int emp_no = Integer.parseInt(req.getParameter("emp_no"));
			
			
			Employee emp = selectEmployeeService.select(emp_no);
			
			Insurance ins = selectInsuranceService.select(emp_no);
			
			List<Education> edu = selectEducationService.selectListByNo(emp_no);
			List<Career> car = selectCareerService.selectListByNo(emp_no);
			Military mil = selectMilitaryService.select(emp_no);
			List<License> lcs = selectLicenseService.selectListByNo(emp_no);
			List<Language> lang = selectLanguageService.selectListByNo(emp_no);
			Retire rtr = selectRetireService.select(emp_no);
			Personnel personnel = new Personnel(emp, ins, edu, car, mil, lcs, lang, rtr);
			req.setAttribute("personnel", personnel);

			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return FORM_VIEW;
	}

}
