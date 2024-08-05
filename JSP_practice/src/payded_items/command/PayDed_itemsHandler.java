package payded_items.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import attvac_items.model.Attend_items;
import attvac_items.model.Vacation_items;
import attvac_items.service.Attend_itemsRequest;
import attvac_items.service.SelectAttend_itemsService;
import mvc.command.CommandHandler;
import payded_items.model.Deduction_items;
import payded_items.model.Payment_items;
import payded_items.service.Deduction_itemsRequest;
import payded_items.service.InsertDeduction_itemsService;
import payded_items.service.InsertPayment_itemsService;
import payded_items.service.Payment_itemsRequest;
import payded_items.service.SelectDeduction_itemsService;
import payded_items.service.SelectPayment_itemsService;

public class PayDed_itemsHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/setting/salary_setting.jsp";

	private InsertPayment_itemsService insertPayService = new InsertPayment_itemsService();
	private SelectPayment_itemsService selectPayService = new SelectPayment_itemsService();
	private InsertDeduction_itemsService insertDedService = new InsertDeduction_itemsService();
	private SelectDeduction_itemsService selectDedService = new SelectDeduction_itemsService();
	private SelectAttend_itemsService selectAttService = new SelectAttend_itemsService();

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
			
			Payment_itemsRequest payReq = null;
			Deduction_itemsRequest dedReq = null;
			
			if(req.getParameter("pay_name") != null) {
				payReq = new Payment_itemsRequest(
						req.getParameter("pay_name"),
						req.getParameter("pay_tax"),
						req.getParameter("tax_free_name"),
						req.getParameter("tax_free_limit"),	
						req.getParameter("tax_memo"),	
						req.getParameter("cut_unit"),	
						req.getParameter("attend_conn"),	
						req.getParameter("pay_cost"),	
						req.getParameter("pay_used")
						);
				
				payReq.validate(errors);
				
				if(!errors.isEmpty()) {	// 에러가있으면 newArticleForm 주소를 반환
					
					List<Payment_items> list_pay = selectPayService.select();
					req.setAttribute("list_pay", list_pay);
					
					List<Deduction_items> list_ded = selectDedService.select();
					req.setAttribute("list_ded", list_ded);
					
					List<Attend_items> list_att = selectAttService.select();
					req.setAttribute("list_att", list_att);
					
					return FORM_VIEW;
				}
				
				Payment_items payment_items = insertPayService.insert(payReq);
			}
			
			if(req.getParameter("ded_name") != null) {
				dedReq = new Deduction_itemsRequest(
						req.getParameter("ded_name"),
						req.getParameter("ded_memo"),
						req.getParameter("ded_cut_unit"),
						req.getParameter("ded_note"),
						req.getParameter("ded_used")
						);
				dedReq.validate(errors);		
				
				
				if(!errors.isEmpty()) {	// 에러가있으면 newArticleForm 주소를 반환
					List<Payment_items> list_pay = selectPayService.select();
					req.setAttribute("list_pay", list_pay);
					
					List<Deduction_items> list_ded = selectDedService.select();
					req.setAttribute("list_ded", list_ded);
					
					List<Attend_items> list_att = selectAttService.select();
					req.setAttribute("list_att", list_att);
					
					return FORM_VIEW;
				}

				Deduction_items deduction_items = insertDedService.insert(dedReq);
  			}
			
			if(req.getParameter("pay_name_picked") != null) {
				
				String name_picked = req.getParameter("pay_name_picked");
				
				Payment_items pay_picked = selectPayService.selectbyName(name_picked);
				req.setAttribute("pay_picked", pay_picked);
			
			}
			
			//공제 이프문 들어갈 곳
			if(req.getParameter("ded_name_picked") != null) {
				
				String name_picked = req.getParameter("ded_name_picked");
				
				Deduction_items ded_picked = selectDedService.selectbyName(name_picked);
				req.setAttribute("ded_picked", ded_picked);
			
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