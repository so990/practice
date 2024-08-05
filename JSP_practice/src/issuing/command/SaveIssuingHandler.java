package issuing.command;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import issuing.model.Issuing;
import issuing.service.InsertIssuingService;
import issuing.service.IssuingRequest;
import issuing.service.SelectIssuingService;
import mvc.command.CommandHandler;
import personnel.model.Employee;
import personnel.service.SelectEmployeeService;

public class SaveIssuingHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/personnel/issuing.jsp";

	private SelectEmployeeService selectEmployeeService = new SelectEmployeeService();
	private SelectIssuingService selectIssuingService = new SelectIssuingService();
	private InsertIssuingService insertIssuingService = new InsertIssuingService();

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
		
		List<Employee> emp_list = selectEmployeeService.selectAll();
		req.setAttribute("emp_list", emp_list);

			try {
				
				int isu_num = selectIssuingService.selectLastNo();
				
				if(isu_num == 0) {
					isu_num = 1;
				}else {
					isu_num += 1;
				}
				
				IssuingRequest isuReq = new IssuingRequest(
						Integer.parseInt(req.getParameter("emp_isu")),
						isu_num,
						req.getParameter("isu_led"),
						req.getParameter("isu_pur"),
						new java.sql.Date(fdate.parse(req.getParameter("isu_date")).getTime())
						);

				Issuing isu = insertIssuingService.insert(isuReq);
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		return FORM_VIEW;
	}

}
