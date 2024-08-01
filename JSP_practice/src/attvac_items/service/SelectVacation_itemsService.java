package attvac_items.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import personnel.dao.Vacation_itemsDao;
import personnel.model.Vacation_items;

public class SelectVacation_itemsService {
	private Vacation_itemsDao vacation_itemsDao = new Vacation_itemsDao();

	public List<Vacation_items> select() {
		try(Connection conn = ConnectionProvider.getConnection()) {

			List<Vacation_items> vacation_items = vacation_itemsDao.selectAll(conn);	
			
			return vacation_items;

		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

}