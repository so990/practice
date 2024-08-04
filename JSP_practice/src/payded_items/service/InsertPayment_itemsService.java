package payded_items.service;

import java.sql.Connection;
import java.sql.SQLException;

import attvac_items.model.Attend_items;
import attvac_items.service.Attend_itemsRequest;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import payded_items.dao.Payment_itemsDao;
import payded_items.model.Payment_items;

public class InsertPayment_itemsService {
	
	private Payment_itemsDao payment_itemsDao = new Payment_itemsDao();
	
	public Payment_items insert(Payment_itemsRequest req) {
		Connection conn = null;
		try {
			conn=ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Payment_items payment_items = toPayment_items(req);
			
			Payment_items savedPayment_items= payment_itemsDao.insert(conn, payment_items);
			if(savedPayment_items == null) {
				throw new RuntimeException("fail to insert payment_items");
			}
			
			conn.commit();
			
			return savedPayment_items;
			
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch(RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	private Payment_items toPayment_items(Payment_itemsRequest req) {
		return new Payment_items (
				req.getPay_name(),
				req.getPay_tax(),
				req.getTax_free_name(),
				req.getTax_free_limit(),
				req.getTax_memo(),
				req.getCut_unit(),
				req.getAttend_conn(),
				req.getPay_cost(),
				req.getPay_used());
	}
}
