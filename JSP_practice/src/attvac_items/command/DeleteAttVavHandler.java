package attvac_items.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import attvac_items.model.Attend_items;
import attvac_items.model.Vacation_items;
import attvac_items.service.DeleteAttService;
import attvac_items.service.DeleteVacService;
import attvac_items.service.SelectAttend_itemsService;
import attvac_items.service.SelectVacation_itemsService;
import mvc.command.CommandHandler;
import payded_items.model.Deduction_items;
import payded_items.model.Payment_items;

public class DeleteAttVavHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/setting/vacation.jsp";

	private SelectAttend_itemsService selectAttService = new SelectAttend_itemsService();
	private SelectVacation_itemsService selectVacService = new SelectVacation_itemsService();
	private DeleteAttService deleteAttService = new DeleteAttService();
	private DeleteVacService deleteVacService = new DeleteVacService();	

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
		
		List<Attend_items> list_att = selectAttService.select();
		req.setAttribute("list_att", list_att);
		
		List<Vacation_items> list_vac = selectVacService.select();
		req.setAttribute("list_vac", list_vac);

		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		try { 
			
			if(req.getParameter("del_att_name") != null) {
				
				String name = req.getParameter("del_att_name");
				
				deleteAttService.delete(name);
				
			}
			
			if(req.getParameter("update_sal_name") != null) {
				
				String name = req.getParameter("del_sal_name");
				
				//deletePayService.delete(name);
				
			}
			
			if(req.getParameter("del_vac_name") != null) {
				
				String name = req.getParameter("del_vac_name");
				
				deleteVacService.delete(name);
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Attend_items> list_att = selectAttService.select();
		req.setAttribute("list_att", list_att);
		
		List<Vacation_items> list_vac = selectVacService.select();
		req.setAttribute("list_vac", list_vac);
		
		return FORM_VIEW;	
		//newArticleSuccess 주소를 반환
		
	}

}