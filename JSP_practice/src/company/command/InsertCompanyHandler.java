package company.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import company.model.Company;
import company.service.CompanyRequest;
import company.service.InsertCompanyService;
import company.service.SelectCompanyService;
import mvc.command.CommandHandler;

public class InsertCompanyHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/setting/userInfo.jsp";
	private InsertCompanyService insertService = new InsertCompanyService();
	private SelectCompanyService selectService = new SelectCompanyService();

	// DATE형 입출력이 있을때 사용하세요
	SimpleDateFormat fdate = new SimpleDateFormat("yyyy-MM-dd"); // 같은 형식으로 맞춰줌
	Date founded_date;
	Date calc_start;
	Date calc_end;
	Date payday;

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

		Company company = selectService.select("1"); // #########

		if (!(company == null)) {
			req.setAttribute("company", company);
		}
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		try { 
			if (req.getParameter("founded_date") == null || req.getParameter("founded_date").isEmpty()) {
				founded_date = null;
			}else {
				founded_date = Date.valueOf(req.getParameter("founded_date"));
			}
			if (req.getParameter("calc_start") == null || req.getParameter("calc_start").isEmpty()) {
				calc_start = null;
			}else {
				calc_start = Date.valueOf(req.getParameter("calc_start"));
			}
			if (req.getParameter("calc_end") == null || req.getParameter("calc_end").isEmpty()) {
				calc_end = null;
			}else {
				calc_end = Date.valueOf(req.getParameter("calc_end"));
			}
			if (req.getParameter("payday") == null || req.getParameter("payday").isEmpty()) {
				payday = null;
			}else {
				payday = Date.valueOf(req.getParameter("payday"));
			}

			CompanyRequest cpnReq = new CompanyRequest(
					req.getParameter("cp_name"), 
					req.getParameter("ceo_job"),
					req.getParameter("ceo_name"), 
					req.getParameter("bs_num"), 
					req.getParameter("bs_regnum"),
					founded_date,
					req.getParameter("hp"), 
					req.getParameter("bs_post"), 
					req.getParameter("bs_addr"),
					req.getParameter("bs_phone"), 
					req.getParameter("bs_fax"), 
					req.getParameter("bs_type"),
					req.getParameter("cp_type"), 
					calc_start,
					calc_end,
					payday,
					req.getParameter("bs_bank"),
					req.getParameter("bs_account"), 
					req.getParameter("bs_acc_name"));

			cpnReq.validate(errors);

			if (!errors.isEmpty()) { // 에러가있으면 newArticleForm 주소를 반환
				return FORM_VIEW;
			}

			// 작성과 DB에 저장이 성공적으로 완료되면 저장
			Company company = insertService.insert(cpnReq);
			// company를 Request 영역에 저장
			req.setAttribute("company", company);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return FORM_VIEW; // 수정필요. 새창만들기? 리퀘스트에 올려서 팝업띄우기?
		// newArticleSuccess 주소를 반환
	}

}
