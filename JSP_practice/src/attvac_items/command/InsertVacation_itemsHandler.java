package attvac_items.command;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import attvac_items.service.InsertVacation_itemsService;
import attvac_items.service.SelectVacation_itemsService;
import attvac_items.service.Vacation_itemsRequest;
import mvc.command.CommandHandler;
import personnel.model.Vacation_items;

public class InsertVacation_itemsHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/attend/vacation.jsp";
	private InsertVacation_itemsService insertService = new InsertVacation_itemsService();
	private SelectVacation_itemsService selectService = new SelectVacation_itemsService();
	
	// DATE형 입출력이 있을때 사용하세요
	SimpleDateFormat fdate = new SimpleDateFormat("yyyy-MM-dd"); //같은 형식으로 맞춰줌
    Date vac_start;
    Date vac_end;

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("get")) {		//get 요청이 오면 FORM_VIEW
			return processForm(req,res);
		}else if(req.getMethod().equalsIgnoreCase("post")) {//post 요청이 오면 processSubmit()로 이동
			return processSubmit(req,res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		
		List<Vacation_items> list_vac = selectService.select();
		req.setAttribute("list_vac", list_vac);
		
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
			
			Vacation_itemsRequest vacReq = new Vacation_itemsRequest(
					req.getParameter("vac_name"),
					vac_start,
					vac_end,
					req.getParameter("vac_used")
					);
				
			vacReq.validate(errors);
			
			if(!errors.isEmpty()) {	// 에러가있으면 newArticleForm 주소를 반환
				return FORM_VIEW;
		}
		
		//작성과 DB에 저장이 성공적으로 완료되면 저장
		Vacation_items vacation_items = insertService.insert(vacReq);
		
		List<Vacation_items> list_vac = selectService.select();
		req.setAttribute("list_vac", list_vac);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return FORM_VIEW;	// 수정필요. 새창만들기? 리퀘스트에 올려서 팝업띄우기?
		//newArticleSuccess 주소를 반환
	}
	
}
