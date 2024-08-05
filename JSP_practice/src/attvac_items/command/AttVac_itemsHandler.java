package attvac_items.command;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import attvac_items.model.Attend_items;
import attvac_items.model.Vacation_days_setting;
import attvac_items.model.Vacation_items;
import attvac_items.service.Attend_itemsRequest;
import attvac_items.service.InsertAttend_itemsService;
import attvac_items.service.InsertVacation_itemsService;
import attvac_items.service.SelectAttend_itemsService;
import attvac_items.service.SelectVacation_itemsService;
import attvac_items.service.Vacation_itemsRequest;
import mvc.command.CommandHandler;
import payded_items.model.Payment_items;

public class AttVac_itemsHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/setting/vacation.jsp";

	private InsertAttend_itemsService insertAttService = new InsertAttend_itemsService();
	private SelectAttend_itemsService selectAttService = new SelectAttend_itemsService();
	private InsertVacation_itemsService insertVacService = new InsertVacation_itemsService();
	private SelectVacation_itemsService selectVacService = new SelectVacation_itemsService();

	SimpleDateFormat fdate = new SimpleDateFormat("yyyy-MM-dd"); // 같은 형식으로 맞춰줌
	Date vac_start;
	Date vac_end;

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

		List<Vacation_items> list_vac = selectVacService.select();
		req.setAttribute("list_vac", list_vac);

		List<Attend_items> list_att = selectAttService.select();
		req.setAttribute("list_att", list_att);

		return FORM_VIEW;	
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		try { 
			
			if (req.getParameter("vac_start") == null || req.getParameter("vac_start").isEmpty()) {
				vac_start = null;
			}else {
				vac_start = Date.valueOf(req.getParameter("vac_start"));
			}
			
			if (req.getParameter("vac_end") == null || req.getParameter("vac_end").isEmpty()) {
				vac_end = null;
			}else {
				vac_end = Date.valueOf(req.getParameter("vac_end"));
			}
			
			////////////////////////////////////////////
			
			Vacation_itemsRequest vacReq = null;
			Attend_itemsRequest attReq = null;
			
			if(req.getParameter("vac_name") != null) {
				vacReq = new Vacation_itemsRequest(
						req.getParameter("vac_name"),
						vac_start,
						vac_end,
						req.getParameter("vac_used")
						);
				
				vacReq.validate(errors);
				
				if(!errors.isEmpty()) {	// 에러가있으면 newArticleForm 주소를 반환
					
					List<Vacation_items> list_vac = selectVacService.select();
					req.setAttribute("list_vac", list_vac);
					
					List<Attend_items> list_att = selectAttService.select();
					req.setAttribute("list_att", list_att);
					
					return FORM_VIEW;
				}
				
				Vacation_items vacation_items = insertVacService.insert(vacReq);
			}
			
			if(req.getParameter("att_name") != null) {
				attReq = new Attend_itemsRequest(
						req.getParameter("att_name"),
						req.getParameter("att_unit"),
						req.getParameter("att_grp"),
						req.getParameter("att_deduction"),
						req.getParameter("att_conn"),
						req.getParameter("att_used")
						);
				attReq.validate(errors);		
				
				
				if(!errors.isEmpty()) {	// 에러가있으면 newArticleForm 주소를 반환
					List<Vacation_items> list_vac = selectVacService.select();
					req.setAttribute("list_vac", list_vac);
					
					List<Attend_items> list_att = selectAttService.select();
					req.setAttribute("list_att", list_att);
					
					return FORM_VIEW;
				}

				Attend_items attend_items = insertAttService.insert(attReq);  

			}
				if(req.getParameter("vac_name_picked") != null) {
				
					String name_picked = req.getParameter("vac_name_picked");
					
					Vacation_items vac_picked = selectVacService.selectbyName(name_picked);
					req.setAttribute("vac_picked", vac_picked);
			
			}
				//근태 이프문 들어갈 곳
				if(req.getParameter("att_name_picked") != null) {
					
					String name_picked = req.getParameter("att_name_picked");
					
					Attend_items att_picked = selectAttService.selectbyName(name_picked);
					req.setAttribute("att_picked", att_picked);
			
			}
				

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Vacation_items> list_vac = selectVacService.select();
		req.setAttribute("list_vac", list_vac);
		
		List<Attend_items> list_att = selectAttService.select();
		req.setAttribute("list_att", list_att);
		
		return FORM_VIEW;	// 수정필요. 새창만들기? 리퀘스트에 올려서 팝업띄우기?
		//newArticleSuccess 주소를 반환		
	}
}