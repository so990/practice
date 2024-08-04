package personnel.command;

import java.sql.Date;
import java.text.SimpleDateFormat;
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
import personnel.service.CareerRequest;
import personnel.service.EducationRequest;
import personnel.service.EmployeeRequest;
import personnel.service.InsertAppointService;
import personnel.service.InsertCareerService;
import personnel.service.InsertEducationService;
import personnel.service.InsertEmployeeService;
import personnel.service.InsertFamilyService;
import personnel.service.InsertInsuranceService;
import personnel.service.InsertLanguageService;
import personnel.service.InsertLicenseService;
import personnel.service.InsertMilitaryService;
import personnel.service.InsertRetireService;
import personnel.service.InsertRewardService;
import personnel.service.InsertStudyService;
import personnel.service.InsuranceRequest;
import personnel.service.LanguageRequest;
import personnel.service.LicenseRequest;
import personnel.service.MilitaryRequest;
import personnel.service.RetireRequest;
import personnel.service.SelectAppointService;
import personnel.service.SelectCareerService;
import personnel.service.SelectEducationService;
import personnel.service.SelectEmployeeService;
import personnel.service.SelectFamilyService;
import personnel.service.SelectInsuranceService;
import personnel.service.SelectLanguageService;
import personnel.service.SelectLicenseService;
import personnel.service.SelectMilitaryService;
import personnel.service.SelectRetireService;
import personnel.service.SelectRewardService;
import personnel.service.SelectStudyService;

public class InsertPersonnelHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/setting/empRegister.jsp";

	private InsertCareerService insertCareerService = new InsertCareerService();
	private InsertEducationService insertEducationService = new InsertEducationService();
	private InsertEmployeeService insertEmployeeService = new InsertEmployeeService();
	private InsertInsuranceService insertInsuranceService = new InsertInsuranceService();
	private InsertLanguageService insertLanguageService = new InsertLanguageService();
	private InsertLicenseService insertLicenseService = new InsertLicenseService();
	private InsertMilitaryService insertMilitaryService = new InsertMilitaryService();
	private InsertRetireService insertRetireService = new InsertRetireService();

	private SelectCareerService selectCareerService = new SelectCareerService();
	private SelectEducationService selectEducationService = new SelectEducationService();
	private SelectEmployeeService selectEmployeeService = new SelectEmployeeService();
	private SelectInsuranceService selectInsuranceService = new SelectInsuranceService();
	private SelectLanguageService selectLanguageService = new SelectLanguageService();
	private SelectLicenseService selectLicenseService = new SelectLicenseService();
	private SelectMilitaryService selectMilitaryService = new SelectMilitaryService();
	private SelectRetireService selectRetireService = new SelectRetireService();

	// DATE형 입출력이 있을때 사용하세요
	SimpleDateFormat fdate = new SimpleDateFormat("yyyy-MM-dd"); // 같은 형식으로 맞춰줌
	Date appo_date;
	Date hired_date;
	Date retired_date;

	@Override
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

		int emp_no = selectEmployeeService.selectLastNo();
		int new_no = emp_no + 1;

//		Employee emp = selectEmployeeService.select(emp_no);
//		Insurance ins = selectInsuranceService.select(emp_no);
//		List<Education> edu = selectEducationService.selectListByNo(emp_no);
//		List<Career> car = selectCareerService.selectListByNo(emp_no);
//		Military mil = selectMilitaryService.select(emp_no);
//		List<License> lcs = selectLicenseService.selectListByNo(emp_no);
//		List<Language> lang = selectLanguageService.selectListByNo(emp_no);
//		Retire rtr = selectRetireService.select(emp_no);
//
//		
//		Personnel personnel = new Personnel(emp, ins, edu, car, mil, lcs, lang, rtr);
//		req.setAttribute("personnel", personnel);

		req.setAttribute("New_no", new_no);
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		if ((req.getParameter("emp_search")==null || req.getParameter("emp_search").isEmpty()) && req.getParameter("emp_no")!=null) {
		
		try { 
			if (req.getParameter("appo_date") == null || req.getParameter("appo_date").isEmpty()) {
				appo_date = null;
			}else {
				appo_date = Date.valueOf(req.getParameter("founded_date"));
			}
			if (req.getParameter("hired_date") == null || req.getParameter("hired_date").isEmpty()) {
				hired_date = null;
			}else {
				hired_date = Date.valueOf(req.getParameter("hired_date"));
			}
			if (req.getParameter("retired_date") == null || req.getParameter("retired_date").isEmpty()) {
				retired_date = null;
			}else {
				retired_date = Date.valueOf(req.getParameter("retired_date"));
			}
			
			
			
			CareerRequest carReq_1 = new CareerRequest(
					Integer.parseInt(req.getParameter("emp_no")),
					req.getParameter("1firm"),
					req.getParameter("1firm_start"),
					req.getParameter("1firm_end"),
					req.getParameter("1firm_term"),
					req.getParameter("1firm_job"),
					req.getParameter("1firm_task"),
					req.getParameter("1firm_retire"));
			CareerRequest carReq_2 = new CareerRequest(
					Integer.parseInt(req.getParameter("emp_no")),
					req.getParameter("2firm"),
					req.getParameter("2firm_start"),
					req.getParameter("2firm_end"),
					req.getParameter("2firm_term"),
					req.getParameter("2firm_job"),
					req.getParameter("2firm_task"),
					req.getParameter("2firm_retire"));
			
			EducationRequest h_eduReq = new EducationRequest(
					Integer.parseInt(req.getParameter("emp_no")),
					req.getParameter("hschool"),
					req.getParameter("hschool_start"),
					req.getParameter("hschool_end"),
					req.getParameter("hschool_name"),
					req.getParameter("hschool_major"),
					req.getParameter("hschool_state")
					);
			EducationRequest u_eduReq = new EducationRequest(
					Integer.parseInt(req.getParameter("emp_no")),
					req.getParameter("uschool"),
					req.getParameter("uschool_start"),
					req.getParameter("uschool_end"),
					req.getParameter("uschool_name"),
					req.getParameter("uschool_major"),
					req.getParameter("uschool_state")
					);
			
			EmployeeRequest empReq = new EmployeeRequest(
					Integer.parseInt(req.getParameter("emp_no")),
					req.getParameter("emp_type"),
					req.getParameter("name_kor"),
					req.getParameter("name_eng"),
					hired_date,
					req.getParameter("dept"),
					req.getParameter("job"),
					"재직",								// 퇴직 표시 미구현
					req.getParameter("nationality"),
					req.getParameter("id_number"),
					req.getParameter("post_code"),
					req.getParameter("addr"),
					req.getParameter("home_number"),
					req.getParameter("phone"),
					req.getParameter("email"),
					req.getParameter("sns"),
					req.getParameter("note"),
					req.getParameter("bank"),
					req.getParameter("account")
					);
			
			InsuranceRequest insReq = new InsuranceRequest(
					Integer.parseInt(req.getParameter("emp_no")),
					req.getParameter("pension"),
					req.getParameter("insur_heal"),
					req.getParameter("insur_care"),
					req.getParameter("insur_hire"),
					req.getParameter("gapgeunse"),
					req.getParameter("wage_earner_per"),
					req.getParameter("youth_red"),
					req.getParameter("durunuri"),
					req.getParameter("salary"),
					req.getParameter("pension_month"),
					req.getParameter("heal_month"),
					req.getParameter("hire_month"),
					req.getParameter("salary_bank"),
					req.getParameter("salary_account"),
					req.getParameter("pension_num"),
					req.getParameter("pension_start"),
					req.getParameter("pension_end"),
					req.getParameter("heal_num"),
					req.getParameter("heal_start"),
					req.getParameter("heal_end"),
					req.getParameter("hire_num"),
					req.getParameter("hire_start"),
					req.getParameter("hire_end"),
					req.getParameter("indus_num"),
					req.getParameter("indus_start"),
					req.getParameter("indus_end")
					);
			
			MilitaryRequest milReq = new MilitaryRequest(
					Integer.parseInt(req.getParameter("emp_no")),
					req.getParameter("mil"),
					req.getParameter("mil_type"),
					req.getParameter("mil_start"),
					req.getParameter("mil_end"),
					req.getParameter("mil_rank"),
					req.getParameter("mil_job"),
					req.getParameter("mil_no_reason")
					);

			LicenseRequest lscReq_1 = new LicenseRequest(
					Integer.parseInt(req.getParameter("emp_no")),
					req.getParameter("lsc_name1"),
					req.getParameter("lsc_date1"),
					req.getParameter("lsc_dep1"),
					req.getParameter("lsc_num1"),
					req.getParameter("lsc_note1")
					);
			LicenseRequest lscReq_2 = new LicenseRequest(
					Integer.parseInt(req.getParameter("emp_no")),
					req.getParameter("lsc_name2"),
					req.getParameter("lsc_date2"),
					req.getParameter("lsc_dep2"),
					req.getParameter("lsc_num2"),
					req.getParameter("lsc_note2")
					);
			
			LanguageRequest langReq_1 = new LanguageRequest(
					Integer.parseInt(req.getParameter("emp_no")),
					req.getParameter("lang_name1"),
					req.getParameter("lang_test1"),
					req.getParameter("lang_score1"),
					req.getParameter("lang_date1"),
					req.getParameter("lang_read1"),
					req.getParameter("lang_listen1"),
					req.getParameter("lang_speak1")
					);
			LanguageRequest langReq_2 = new LanguageRequest(
					Integer.parseInt(req.getParameter("emp_no")),
					req.getParameter("lang_name2"),
					req.getParameter("lang_test2"),
					req.getParameter("lang_score2"),
					req.getParameter("lang_date2"),
					req.getParameter("lang_read2"),
					req.getParameter("lang_listen2"),
					req.getParameter("lang_speak2")
					);
				
			RetireRequest retireReq = new RetireRequest(
					Integer.parseInt(req.getParameter("emp_no")),
					req.getParameter("retire_type"),
					retired_date,
					req.getParameter("retire_reason"),
					req.getParameter("retire_phone"),
					req.getParameter("retire_cost")
					);

			// 작성과 DB에 저장이 성공적으로 완료되면 저장
			Employee emp = insertEmployeeService.insert(empReq);
			Career car_1 = insertCareerService.insert(carReq_1);
			Career car_2 = insertCareerService.insert(carReq_2);
			Education h_edu = insertEducationService.insert(h_eduReq);
			Education u_edu = insertEducationService.insert(u_eduReq);
			Insurance ins = insertInsuranceService.insert(insReq);
			Military mil = insertMilitaryService.insert(milReq);	
			License lsc_1 = insertLicenseService.insert(lscReq_1);
			License lsc_2 = insertLicenseService.insert(lscReq_2);
			Language lang_1 = insertLanguageService.insert(langReq_1);
			Language lang_2 = insertLanguageService.insert(langReq_2);
			Retire retire = insertRetireService.insert(retireReq);
			
			
			req.setAttribute("New_no",emp.getEmp_no() + 1);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		System.out.println("0");
		if ((req.getParameter("emp_no")==null||req.getParameter("emp_no").isEmpty())&& req.getParameter("emp_search")!=null) {
			System.out.println("1");
			try { 
				
				int emp_search = Integer.parseInt(req.getParameter("emp_search"));
				
				Employee emp = selectEmployeeService.select(emp_search);
			      Insurance ins = selectInsuranceService.select(emp_search);
			      List<Education> edu = selectEducationService.selectListByNo(emp_search);
			      List<Career> car = selectCareerService.selectListByNo(emp_search);
			      Military mil = selectMilitaryService.select(emp_search);
			      List<License> lcs = selectLicenseService.selectListByNo(emp_search);
			      List<Language> lang = selectLanguageService.selectListByNo(emp_search);
			      Retire rtr = selectRetireService.select(emp_search);
			      System.out.println("2");
			      
			      Personnel personnel = new Personnel(emp, ins, edu, car, mil, lcs, lang, rtr);
			      req.setAttribute("personnel", personnel);
			      System.out.println(personnel.getEmp().getEmp_no());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return FORM_VIEW; //
		// newArticleSuccess 주소를 반환
	}

}