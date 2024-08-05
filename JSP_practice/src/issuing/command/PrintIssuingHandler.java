package issuing.command;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import company.model.Company;
import company.service.SelectCompanyService;
import mvc.command.CommandHandler;
import personnel.model.Career;
import personnel.model.Employee;
import personnel.model.Retire;
import personnel.service.SelectCareerService;
import personnel.service.SelectEmployeeService;
import personnel.service.SelectRetireService;

public class PrintIssuingHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/personnel/issuing.jsp";

	private SelectCompanyService selectService = new SelectCompanyService();
	private SelectCareerService selectCareerService = new SelectCareerService();
	private SelectEmployeeService selectEmployeeService = new SelectEmployeeService();
	private SelectRetireService selectRetireService = new SelectRetireService();

	SimpleDateFormat fdate = new SimpleDateFormat("yyyy-MM-dd");

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

				Company cpn = selectService.select("1");
				Employee emp = selectEmployeeService.select(emp_no);
				List<Career> car = selectCareerService.selectListByNo(emp_no);
				Retire rtr = selectRetireService.select(emp_no);

				String today = fdate.format(new Date());

				req.setAttribute("cpn", cpn);
				req.setAttribute("emp_pick", emp);
				req.setAttribute("today", today);

			} catch (Exception e) {
				e.printStackTrace();
			}

		return FORM_VIEW;
	}

}
