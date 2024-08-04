package payded_items.service;

import java.sql.Connection;
import java.sql.SQLException;

import attvac_items.model.Vacation_items;
import attvac_items.service.Vacation_itemsRequest;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import payded_items.dao.Deduction_itemsDao;
import payded_items.model.Deduction_items;

public class InsertDeduction_itemsService {
	
	private Deduction_itemsDao deduction_itemsDao = new Deduction_itemsDao();
	
	public Deduction_items insert(Deduction_itemsRequest req) {
		Connection conn = null;
		try {
			conn=ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Deduction_items deduction_items = toDeduction_items(req);
			Deduction_items savedDeduction_items= deduction_itemsDao.insert(conn, deduction_items);
			
			if(savedDeduction_items == null) {
				throw new RuntimeException("fail to insert deduction_items");
			}
			
			conn.commit();
			
			return savedDeduction_items;
			
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
	
	private Deduction_items toDeduction_items(Deduction_itemsRequest req) {
		return new Deduction_items (
				req.getDed_name(),
				req.getDed_memo(),
				req.getDed_cut_unit(),
				req.getDed_note(),
				req.getDed_used());
	}
}
