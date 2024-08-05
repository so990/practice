package attvac_items.command;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import attvac_items.model.Vacation_days;
import attvac_items.model.Vacation_days_setting;
import attvac_items.service.SelectVacation_itemsService;
import attvac_items.service.Vacation_days_settingRequest;
import mvc.command.CommandHandler;

public class VacDays_settingHandler implements CommandHandler {

	private SelectVacation_itemsService selectVacService = new SelectVacation_itemsService();

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

	private String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception{

		List<Vacation_days_setting> list_vac_days = selectVacService.selectM();
		req.setAttribute("list_vac_days", list_vac_days);
		
		System.out.println(list_vac_days.get(0).getEmp_no());

		return "/WEB-INF/view/setting/vac_days_setting.jsp";
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		try { 
			
			if (req.getParameter("hired_date") == null || req.getParameter("hired_date").isEmpty()) {
				hired_date = null;
			}else {
				hired_date = Date.valueOf(req.getParameter("hired_date"));
			}
					
			////////////////////////////////////////////
			
			Vacation_days_settingRequest vacdays = new Vacation_days_settingRequest(
					req.getParameter("emp_type"), 
					Integer.parseInt(req.getParameter("emp_no")), 
					req.getParameter("name_kor"), 
					req.getParameter("dept"), 
					req.getParameter("job"), 
					hired_date, 
					req.getParameter("vac_name"), 
					Integer.parseInt(req.getParameter("has_vac_days"))
					);
				
				if(!errors.isEmpty()) {	// 에러가있으면 newArticleForm 주소를 반환
					
					List<Vacation_days_setting> list_vac_days = selectVacService.selectM();
					req.setAttribute("list_vac_days", list_vac_days);
					
					return "/WEB-INF/view/setting/vac_days_setting.jsp";
				}
				
				List<Vacation_days_setting> vacation_days_setting = selectVacService.selectM();
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Vacation_days_setting> list_vac_days = selectVacService.selectM();
		req.setAttribute("list_vac_days", list_vac_days);
		
		
		return "/WEB-INF/view/setting/vac_days_setting.jsp";	// 수정필요. 새창만들기? 리퀘스트에 올려서 팝업띄우기?
		//newArticleSuccess 주소를 반환		
	}
}