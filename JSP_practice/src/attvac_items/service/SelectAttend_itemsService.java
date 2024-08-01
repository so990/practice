package attvac_items.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import personnel.dao.Attend_itemsDao;
import personnel.model.Attend_items;

public class SelectAttend_itemsService {
	private Attend_itemsDao attend_itemsDao = new Attend_itemsDao();

	public List<Attend_items> select() {
		try(Connection conn = ConnectionProvider.getConnection()) {

			List<Attend_items> attend_items = attend_itemsDao.selectAll(conn);	
			
			return attend_items;

		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

}