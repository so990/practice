package attvac_items.command;

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
import attvac_items.service.InsertAttend_itemsService;
import attvac_items.service.SelectAttend_itemsService;
import attvac_items.service.Vacation_itemsRequest;
import mvc.command.CommandHandler;

public class InsertAttend_itemsHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/attend/vacation.jsp";
	private InsertAttend_itemsService insertService = new InsertAttend_itemsService();
	private SelectAttend_itemsService selectService = new SelectAttend_itemsService();
	
	// DATE형 입출력이 있을때 사용하세요
	SimpleDateFormat fdate = new SimpleDateFormat("yyyy-MM-dd"); //같은 형식으로 맞춰줌
    Date att_unit;

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
		
		List<Attend_items> list_att = selectService.select();
		req.setAttribute("list_att", list_att);
		
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		try { 
			if (req.getParameter("att_unit") == null || req.getParameter("att_unit").isEmpty()) {
				att_unit = null;
			}else {
				att_unit = Date.valueOf(req.getParameter("att_unit"));
			}
			
			Attend_itemsRequest attReq = new Attend_itemsRequest(
					req.getParameter("att_name"),
					att_unit,
					req.getParameter("att_grp"),
					req.getParameter("att_deduction"),
					req.getParameter("att_conn"),
					req.getParameter("att_used")
					);
				
			attReq.validate(errors);
			
			if(!errors.isEmpty()) {	// 에러가있으면 newArticleForm 주소를 반환
				return FORM_VIEW;
		}
		
		//작성과 DB에 저장이 성공적으로 완료되면 저장
		Attend_items attend_items = insertService.insert(attReq);
		
		List<Attend_items> list_att = selectService.select();
		req.setAttribute("list_att", list_att);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return FORM_VIEW;	// 수정필요. 새창만들기? 리퀘스트에 올려서 팝업띄우기?
		//newArticleSuccess 주소를 반환
	}
	
}
