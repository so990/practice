package personnel.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import personnel.service.EmployeeRequest;
import personnel.service.InsertEmployeeService;

public class InsertEmployeeHandler implements CommandHandler {

	private static final String FORM_VIEW = "/empRegister.jsp";
	private InsertEmployeeService insertService = new InsertEmployeeService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws ParseException {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		}else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		}else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	 }
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws ParseException {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		SimpleDateFormat hdate = new SimpleDateFormat("yyyy-MM-dd");
		Date hired_date = hdate.parse(req.getParameter("hired_date"));
		
		Date retired_date = null;
		
		if(!(req.getParameter("retired_date")=="")) {
			
		SimpleDateFormat rdate = new SimpleDateFormat("yyyy-MM-dd");
		retired_date = rdate.parse(req.getParameter("retired_date"));
		
		}
		
		EmployeeRequest employeeReq = new EmployeeRequest(
				Integer.parseInt(req.getParameter("emp_no")),
				req.getParameter("emp_type"),
				req.getParameter("name_kor"),
				req.getParameter("name_eng"),
				hired_date,
				retired_date,
				req.getParameter("dept"),
				req.getParameter("job"),
				req.getParameter("state"),
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
				req.getParameter("account"));
		employeeReq.validate(errors);
		
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		int newEmployeeNo = insertService.insert(employeeReq) + 1;
		req.setAttribute("newEmployeeNo", newEmployeeNo);
		
		return "/empRegister.jsp";
	}
}