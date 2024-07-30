package personnel.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import personnel.dao.Vacation_itemsDao;
import personnel.model.Employee;
import personnel.model.Vacation_items;

public class InsertVacation_itemsService {
	
	private Vacation_itemsDao vacation_itemsDao = new Vacation_itemsDao();
	
	public Vacation_items insert(Vacation_itemsRequest req) {
		Connection conn = null;
		try {
			conn=ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Vacation_items vacation_items = toVacation_items(req);
			Vacation_items savedVacation_items= vacation_itemsDao.insert(conn, vacation_items);
			
			if(savedVacation_items == null) {
				throw new RuntimeException("fail to insert vacation_items");
			}
			
			conn.commit();
			
			return savedVacation_items;
			
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
	
	private Vacation_items toVacation_items(Vacation_itemsRequest req) {
		return new Vacation_items (
				req.getVac_name(),
				req.getVac_start(),
				req.getVac_end(),
				req.getVac_used());
	}
}
