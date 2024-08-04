package attendView.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import attendView.model.AttendView;
import attendView.service.SelectAttendViewService;
import mvc.command.CommandHandler;

public class AttendViewHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/attend/Attend_View.jsp";
	
	//private AttendManageSelectService selectEmpService=new AttendManageSelectService();
	private SelectAttendViewService selectAvService=new SelectAttendViewService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
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
		
		List<AttendView> atdv_list=selectAvService.select();
		req.setAttribute("atdv_list", atdv_list);

		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		return FORM_VIEW;	
	}
	
}
