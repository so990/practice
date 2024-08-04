package personnel.command;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import attvac_items.model.Attend_items;
import attvac_items.model.Vacation_items;
import attvac_items.service.Attend_itemsRequest;
import attvac_items.service.Vacation_itemsRequest;
import mvc.command.CommandHandler;
import personnel.model.EmpSetting;
import personnel.model.Employee;
import personnel.service.EmployeeRequest;
import personnel.service.InsertEmployeeService;
import personnel.service.InsertRetireService;
import personnel.service.SelectEmployeeService;
import personnel.service.SelectRetireService;

public class SelectPersonnelHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/personnel/empSetting.jsp";

	private InsertEmployeeService insertEmpService = new InsertEmployeeService();
	private SelectEmployeeService selectEmpService = new SelectEmployeeService();
	private InsertRetireService insertRetService = new InsertRetireService();
	private SelectRetireService selectRetService = new SelectRetireService();

	SimpleDateFormat fdate = new SimpleDateFormat("yyyy-MM-dd"); // 같은 형식으로 맞춰줌
	Date hired_date;

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

		List<EmpSetting> list_empset = selectEmpService.selectSet();
		req.setAttribute("list_empset", list_empset);

		return FORM_VIEW;	
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		
		return FORM_VIEW;	// 수정필요. 새창만들기? 리퀘스트에 올려서 팝업띄우기?
		//newArticleSuccess 주소를 반환		
	}
}