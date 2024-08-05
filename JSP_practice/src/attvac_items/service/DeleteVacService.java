package attvac_items.service;

import java.sql.Connection;
import java.sql.SQLException;

import attvac_items.dao.Vacation_itemsDao;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class DeleteVacService {
	private Vacation_itemsDao vacDao = new Vacation_itemsDao();

	public void delete(String name) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			vacDao.delete(conn, name);
						
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
