package attvac_items.service;

import java.sql.Connection;
import java.sql.SQLException;

import attvac_items.dao.Attend_itemsDao;
import attvac_items.model.Attend_items;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class ModifyAttService {
	private Attend_itemsDao attDao = new Attend_itemsDao();

	public void update(String before_name, Attend_items att) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			attDao.update(conn, before_name, att);
						
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
