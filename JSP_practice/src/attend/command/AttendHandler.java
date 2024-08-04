package attend.command;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import attend.model.AttendHistory;
import attend.service.AttendHistoryInsertService;
import attend.service.AttendHistorySelectService;
import attend.service.AttendManageSelectService;
import attend.service.AttendManageRequest;
import mvc.command.CommandHandler;
import personnel.model.Employee;

public class AttendHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/attend/Attend_Management.jsp";
	
	private AttendManageSelectService selectEmpService=new AttendManageSelectService();
	
	private AttendHistorySelectService selectAtdService=new AttendHistorySelectService();
	private AttendHistoryInsertService insertAtdService=new AttendHistoryInsertService();
	
	
	SimpleDateFormat fdate = new SimpleDateFormat("yyyy-MM-dd"); 
    Date atth_inserted;
    Date atth_start;
    Date atth_end;
	
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
		
		List<Employee> emp_list=selectEmpService.select();
		req.setAttribute("emp_list", emp_list);
		
		List<AttendHistory> atd_list=selectAtdService.select();
		req.setAttribute("atd_list", atd_list);
		
		
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		System.out.println("eeeeeeeeee");

		try {
			if (req.getParameter("atth_inserted") == null || req.getParameter("atth_inserted").isEmpty()) {
				atth_inserted = null;
			}else {
				atth_inserted = Date.valueOf(req.getParameter("atth_inserted"));
			}
			if (req.getParameter("atth_start") == null || req.getParameter("atth_start").isEmpty()) {
				atth_start = null;
			}else {
				atth_start = Date.valueOf(req.getParameter("atth_start"));
			}
			if (req.getParameter("atth_end") == null || req.getParameter("atth_end").isEmpty()) {
				atth_end = null;
			}else {
				atth_end = Date.valueOf(req.getParameter("atth_end"));
			}
			
			AttendManageRequest insertAtdReq=new AttendManageRequest(
					Integer.parseInt(req.getParameter("emp_no")),
					atth_inserted,
					req.getParameter("atth_name"),
					atth_start,
					atth_end,
					Integer.parseInt(req.getParameter("atth_days")),
					req.getParameter("atth_cost"),
					req.getParameter("atth_note")					
					);
			
			insertAtdReq.validate(errors);
			
			
			
			if(!errors.isEmpty()) {
				
				List<Employee> emp_list=selectEmpService.select();
				req.setAttribute("emp_list", emp_list);
				
				List<AttendHistory> atd_list=selectAtdService.select();
				req.setAttribute("atd_list", atd_list);	
				
				return FORM_VIEW;
			}
		
			AttendHistory ah = insertAtdService.insert(insertAtdReq);
			


			
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		List<Employee> emp_list=selectEmpService.select();
		req.setAttribute("emp_list", emp_list);
		
		List<AttendHistory> atd_list=selectAtdService.select();
		req.setAttribute("atd_list", atd_list);	

		return FORM_VIEW;	// �닔�젙�븘�슂. �깉李쎈쭔�뱾湲�? 由ы�섏뒪�듃�뿉 �삱�젮�꽌 �뙘�뾽�쓣�슦湲�?
		//newArticleSuccess 二쇱냼瑜� 諛섑솚
	}
	
}
