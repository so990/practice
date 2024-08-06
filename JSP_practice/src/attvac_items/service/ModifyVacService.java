package attvac_items.service;

import java.sql.Connection;
import java.sql.SQLException;

import attvac_items.dao.Vacation_itemsDao;
import attvac_items.model.Vacation_items;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class ModifyVacService {
	private Vacation_itemsDao vacDao = new Vacation_itemsDao();

	public void update(String before_name, Vacation_items vac) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			vacDao.update(conn, before_name, vac);
						
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
