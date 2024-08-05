package payded_items.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import attvac_items.model.Attend_items;
import attvac_items.service.SelectAttend_itemsService;
import mvc.command.CommandHandler;
import payded_items.model.Deduction_items;
import payded_items.model.Payment_items;
import payded_items.service.DeleteDedService;
import payded_items.service.DeletePayService;
import payded_items.service.ModifyPayService;
import payded_items.service.SelectDeduction_itemsService;
import payded_items.service.SelectPayment_itemsService;

public class DeletePayDedHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/setting/salary_setting.jsp";

	private SelectPayment_itemsService selectPayService = new SelectPayment_itemsService();
	private SelectDeduction_itemsService selectDedService = new SelectDeduction_itemsService();
	private SelectAttend_itemsService selectAttService = new SelectAttend_itemsService();
	private DeletePayService deletePayService = new DeletePayService();
	private DeleteDedService deleteDedService = new DeleteDedService();	
	private ModifyPayService modifyPayService = new ModifyPayService();

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

		List<Payment_items> list_pay = selectPayService.select();
		req.setAttribute("list_pay", list_pay);

		List<Deduction_items> list_ded = selectDedService.select();
		req.setAttribute("list_ded", list_ded);
		
		List<Attend_items> list_att = selectAttService.select();
		req.setAttribute("list_att", list_att);

		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		try { 
			
			if(req.getParameter("del_sal_name") != null) {
				
				String name = req.getParameter("del_sal_name");
				
				deletePayService.delete(name);
				
			}
			
			
			if(req.getParameter("del_ded_name") != null) {
				
				String name = req.getParameter("del_ded_name");
				
				deleteDedService.delete(name);
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Payment_items> list_pay = selectPayService.select();
		req.setAttribute("list_pay", list_pay);
		
		List<Deduction_items> list_ded = selectDedService.select();
		req.setAttribute("list_ded", list_ded);
		
		List<Attend_items> list_att = selectAttService.select();
		req.setAttribute("list_att", list_att);
		return FORM_VIEW;	
		//newArticleSuccess 주소를 반환
		
	}

}