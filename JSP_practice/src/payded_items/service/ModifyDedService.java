package payded_items.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import payded_items.dao.Deduction_itemsDao;
import payded_items.model.Deduction_items;
import payded_items.model.Payment_items;

public class ModifyDedService {
	private Deduction_itemsDao dedDao = new Deduction_itemsDao();

	public void update(String before_name, Deduction_items ded) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			dedDao.update(conn, before_name, ded);
						
			conn.commit();
		} catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch(Exception e) {
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
