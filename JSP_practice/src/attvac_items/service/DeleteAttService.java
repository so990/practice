package attvac_items.service;

import java.sql.Connection;
import java.sql.SQLException;

import attvac_items.dao.Attend_itemsDao;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class DeleteAttService {
	private Attend_itemsDao attDao = new Attend_itemsDao();

	public void delete(String name) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			attDao.delete(conn, name);
						
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
