package payded_items.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import payded_items.dao.Deduction_itemsDao;
import payded_items.model.Deduction_items;


public class SelectDeduction_itemsService {
	private Deduction_itemsDao deduction_itemsDao = new Deduction_itemsDao();

	public List<Deduction_items> select() {
		try(Connection conn = ConnectionProvider.getConnection()) {

			List<Deduction_items> deduction_items = deduction_itemsDao.selectAll(conn);	
			
			return deduction_items;

		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

}