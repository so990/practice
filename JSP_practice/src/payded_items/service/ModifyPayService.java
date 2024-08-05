package payded_items.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import payded_items.dao.Payment_itemsDao;
import payded_items.model.Payment_items;

public class ModifyPayService {
	private Payment_itemsDao payDao = new Payment_itemsDao();

	public void update(String before_name, Payment_items pay) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			payDao.update(conn, before_name, pay);
						
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
