package issuing.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import issuing.model.Issuing;
import issuing.service.SelectIssuingService;
import mvc.command.CommandHandler;
import personnel.model.Employee;
import personnel.service.SelectEmployeeService;

public class IssuingListHandler implements CommandHandler {
	private SelectIssuingService selectIssuingService = new SelectIssuingService();
	private SelectEmployeeService selectEmployeeService = new SelectEmployeeService();
	
	
	private static final String FORM_VIEW = "/WEB-INF/view/personnel/issuingList.jsp";

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
		
		List<Issuing> isu_list = selectIssuingService.selectAll();
		List<Employee> emp_list = new ArrayList<>();
		
		
		req.setAttribute("isu_list", isu_list);
		req.setAttribute("emp_list", emp_list);
		System.out.println(isu_list.get(0).getEmp_no());
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		
		List<Issuing> isu_list = selectIssuingService.selectAll();
		req.setAttribute("isu_list", isu_list);
		
		return FORM_VIEW;
	}

}
