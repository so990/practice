package attvac_items.service;

import java.sql.Connection;
import java.sql.SQLException;

import attvac_items.dao.Attend_itemsDao;
import attvac_items.model.Attend_items;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class InsertAttend_itemsService {
	
	private Attend_itemsDao attend_itemsDao = new Attend_itemsDao();
	
	public Attend_items insert(Attend_itemsRequest req) {
		Connection conn = null;
		try {
			conn=ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Attend_items attend_items = toAttend_items(req);
			
			Attend_items savedAttend_items= attend_itemsDao.insert(conn, attend_items);
			if(savedAttend_items == null) {
				throw new RuntimeException("fail to insert attend_items");
			}
			
			conn.commit();
			
			return savedAttend_items;
			
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
	
	private Attend_items toAttend_items(Attend_itemsRequest req) {
		return new Attend_items (
				req.getAtt_name(),
				req.getAtt_unit(),
				req.getAtt_grp(),
				req.getAtt_deduction(),
				req.getAtt_conn(),
				req.getAtt_used());
	}
}
